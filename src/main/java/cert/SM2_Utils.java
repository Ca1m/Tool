package cert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.netcert.framework.crypto.HSM;
import cn.com.infosec.netcert.framework.crypto.IHSM;
import cn.com.infosec.util.Base64;
import utils.Utils;

public class SM2_Utils {

	// 产生密钥对:并返回 string形式
	public static Object[] generateKeyPair(String alg, String keysize) {

		Object[] pair = generateKeyPair(alg, Integer.parseInt(keysize));
		PublicKey pubKey = (PublicKey) pair[0];
		PrivateKey priKey = (PrivateKey) pair[1];
		Object[] obj = new Object[] { pubKey, priKey };

		return obj;
	}

	// 产生密钥对
	private static Object[] generateKeyPair(String alg, int keysize) {
		// 初始化 密钥对生成器
		KeyPairGenerator gen = null;
		try {
			if ("SM2".equalsIgnoreCase(alg)) {
				ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
				gen = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
				gen.initialize(sm2Spec);
				gen.initialize(sm2Spec, new SecureRandom());
			} else {
				gen = KeyPairGenerator.getInstance(alg);
				gen.initialize(keysize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取 公私钥对象
		KeyPair keyPair = gen.generateKeyPair();
		PublicKey pubKey = keyPair.getPublic();
		PrivateKey priKey = keyPair.getPrivate();
		// 返回对象
		Object[] pair = new Object[] { pubKey, priKey };
		return pair;
	}

	// 2. 签名，并返回
	public static String generateSignature(String src, PrivateKey priKey) {
		String signatureValue = null;
		try {
			// 1. 选择算法
			Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),
					new BouncyCastleProvider());
			;
			// 2. 签名
			signature.initSign(priKey);
			signature.update(src.getBytes());
			byte[] signByte = signature.sign();
			signatureValue = Base64.encode(signByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signatureValue;
	}

	// 3. 验签
	public static boolean veritySignature(String src, PublicKey pubKey, String signatureValue) {
		boolean flag = false;
		try {
			// 1. 选择 验签算法
			Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),
					new BouncyCastleProvider());
			;
			// 2. 验证签名
			signature.initVerify(pubKey);
			signature.update(src.getBytes());
			byte[] sign_bs = signatureValue.getBytes();
			flag = signature.verify(sign_bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 从Base64的证书字符串中获取 各项数据 : info
	public static String getCertInfo(File certFile, String certStr) {
		StringBuffer sb = new StringBuffer();
		try {
			if (certFile != null) {
				FileInputStream fin_1 = new FileInputStream(certFile);
				byte[] bytes = new byte[fin_1.available()];
				fin_1.read(bytes);
				fin_1.close();
				certStr = Base64.encode(bytes);
			}
			byte[] cerBin = Base64.decode(certStr); // 解析 Base64
			CertificateFactory cf = CertificateFactory.getInstance("X.509", "INFOSEC");
			X509Certificate c = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(cerBin));

			sb.append("SerialNumber: " + c.getSerialNumber() + "\n");
			sb.append("subjectDN: " + c.getSubjectDN() + "\n");
			sb.append("Issuer: " + c.getIssuerDN() + "\n");
			sb.append("Pubkey: " + Base64.encode(c.getPublicKey().getEncoded()) + "\n");
			sb.append("notBefore: " + Utils.format(c.getNotBefore()) + "\n");
			sb.append("notAfter: " + Utils.format(c.getNotAfter()) + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	
	
	
	// 验证签名 : info
	public static boolean verifySign(File certFile, String certStr, String src, String signature) {
		try {
			Security.addProvider(new InfosecProvider());
			X509Certificate cert = null;
			if (certFile == null) {
				CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "INFOSEC");
				cert = (X509Certificate) certFactory
						.generateCertificate(new ByteArrayInputStream(Base64.decode(certStr)));
			} else {
				FileInputStream fin_1 = new FileInputStream(certFile);
				byte[] bytes = new byte[fin_1.available()];
				fin_1.read(bytes);
				fin_1.close();
				certStr = Base64.encode(bytes);
				byte[] cerBin = Base64.decode(certStr); // 解析 Base64
				CertificateFactory cf = CertificateFactory.getInstance("X.509", "INFOSEC");
				cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(cerBin));
			}

			IHSM hsm = HSM.getInst("");
			if (hsm.verify(Base64.decode(src), Base64.decode(signature), cert.getPublicKey(), "SM3withSM2",
					"1234567812345678")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
}








