package cert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import top.yancc.java.time.Test_time_2;

public class SM2_Utils {

	public static void main(String[] args) throws IOException {
		Security.addProvider(new InfosecProvider());

		System.out.println("\n产生SM2密钥对：");
		Object[] keyPair = generateKeyPair("SM2", Integer.parseInt("256"));
		PublicKey pubKey = (PublicKey) keyPair[0];
		PrivateKey priKey = (PrivateKey) keyPair[1];
		parseString(keyPair); // 输出 密钥对

		System.out.println("\n使用自己产生的SM2密钥签名：");
		String src = "111111111";
		byte[] signByte = signature(src, priKey);
		

		System.out.println("\n使用自己的公钥验证签名值：");
		veritySignature(src, pubKey, signByte);
		
		
		System.out.println("\n验证签名值：");
		String src_ = "<source><header><ID>f29af7</ID><TASKTAG>56822</TASKTAG><LOCALTIME>1593758166900</LOCALTIME><VERSION>0</VERSION><TYPE>LOGIN</TYPE></header></source>";
		String certStr_ = "MIIB7TCCAZGgAwIBAgIEAPKa9zAMBggqgRzPVQGDdQUAMEMxCzAJBgNVBAYTAmNuMQwwCgYDVQQKDANzZWMxDTALBgNVBAsMBGluZm8xFzAVBgNVBAMMDmNhXzYzX2hzbV9vY3NwMB4XDTIwMDYxODA5NTExOFoXDTIxMDYxODA5NTExOFowVzELMAkGA1UEBhMCY24xDDAKBgNVBAoMA3NlYzENMAsGA1UECwwEaW5mbzEXMBUGA1UECwwOYWRtaW5pc3RyYXRvcnMxEjAQBgNVBAMMCWJvX2hzbV8wMjBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABN9s6YOogco47hwkjAispvwjMvbjXZjCHm6Ld05A+Kh2EQMN4fCceagRCdTdg3EWz3CET87L0F5bwMebEzlegomjXTBbMB8GA1UdIwQYMBaAFO3SWzcmfNH8Qwrs0cTycBt9p/e6MAkGA1UdEwQCMAAwDgYDVR0PAQH/BAQDAgeAMB0GA1UdDgQWBBS+nZ67wQ0QJ61II2DDr8b3MQYHZDAMBggqgRzPVQGDdQUAA0gAMEUCIQCWqQ6wF2a0KpuGT0MEe7Iaoi1GqXKnsl+5UKL+Ccb8RwIgYnuM8vRO0OzBwtikZxn701FAgCrM3/8zAmDMvgUJkdM=";
		String sign_ = "MEUCIQDFI0nRpcfEF4jtojMLCdI6ijl2qlz7ikgUV93Lckb2pgIgOcdQf0Muh32uU6GqaFmpZ0e7mTPWq5tjpjCE5r4VuBo=";
		
		PublicKey pubkey = getPublicKey(null, certStr_);
		veritySignature(src_, pubkey, Base64.decode(sign_));
	}

	// 1. 产生密钥对
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
	public static byte[] signature(String src, PrivateKey priKey) {
		String signatureValue = null;
		byte[] signByte = null;
		try {
			// 1. 选择算法
			Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),
					new BouncyCastleProvider());
			// 2. 签名
			signature.initSign(priKey);
			signature.update(src.getBytes());
			signByte = signature.sign();
			signatureValue = Base64.encode(signByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("签名值：" + signatureValue);
		return signByte;
	}

	// 3. 验签
	public static void veritySignature(String src, PublicKey pubKey, byte[] sign_bs) {
		boolean flag = false;
		try {
			// 1. 选择 验签算法
			Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),
					new BouncyCastleProvider());
			// 2. 验证签名
			signature.initVerify(pubKey);
			signature.update(src.getBytes());
			flag = signature.verify(sign_bs);
			if (flag) {
				System.out.println("验签成功！！！");
			} else {
				System.out.println("验签失败！！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 从Base64的证书字符串中获取 各项数据 输出,并获取公钥
	 * @param certFile
	 * @param certStr
	 * @return
	 */
	public static PublicKey getPublicKey(File certFile, String certStr) {
		X509Certificate c = null;
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
			c = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(cerBin));

			sb.append("SerialNumber: " + c.getSerialNumber() + "\n");
			sb.append("subjectDN: " + c.getSubjectDN() + "\n");
			sb.append("Issuer: " + c.getIssuerDN() + "\n");
			sb.append("Pubkey: " + Base64.encode(c.getPublicKey().getEncoded()) + "\n");
			sb.append("notBefore: " + Test_time_2.format(c.getNotBefore()) + "\n");
			sb.append("notAfter: " + Test_time_2.format(c.getNotAfter()) + "\n");

			System.out.println(c.getPublicKey());
			
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getPublicKey();
	}

	
	/**
	 * 使用证书文件 或者 Base64 格式的 数据解析 公钥，验证签名值
	 * @param certFile, certStr, src, signature
	 * @return boolean
	 */
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
	
	/**
	 * 将密钥对 格式化为 String 输出
	 * @param keyPair
	 * @return
	 * @throws IOException
	 */
	public static String[] parseString(Object[] keyPair) throws IOException {

		PublicKey pubKey = (PublicKey) keyPair[0];
		PrivateKey priKey = (PrivateKey) keyPair[1];
		Object[] obj = new Object[] { pubKey, priKey };

		byte[] pub_bs = pubKey.getEncoded();
		byte[] pri_bs = priKey.getEncoded();
		String pub_Str = Base64.encode(pub_bs);
		String pri_Str = Base64.encode(pri_bs);

		String[] ss = new String[2];
		ss[0] = pub_Str;
		ss[1] = pri_Str;

		System.out.println("公钥信息：" + ss[0]);
		System.out.println("私钥信息：" + ss[1]);
		return ss;
	}

	

}
