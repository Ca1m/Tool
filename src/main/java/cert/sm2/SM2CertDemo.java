package cert.sm2;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.X509KeyUsage;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Base64;

/**
 * SM2 X.509签名制作Demo
 *
 * @author jy
 * @date 2018-12-21 14:04
 */
public class SM2CertDemo {

	/**
	 * BouncyCastle算法提供者
	 */
	private static final Provider BC = new BouncyCastleProvider();

	/**
	 * 获取扩展密钥用途
	 *
	 * @return 增强密钥用法ASN.1对象
	 * @author Cliven
	 * @date 2018-12-21 16:04:58
	 */
	public static DERSequence extendedKeyUsage() {
		// // 客户端身份认证
		// ASN1ObjectIdentifier clientAuth = new
		// ASN1ObjectIdentifier("1.3.6.1.5.5.7.3.2");
		// // 安全电子邮件
		// ASN1ObjectIdentifier emailProtection = new
		// ASN1ObjectIdentifier("1.3.6.1.5.5.7.3.4");
		// 构造容器对象
		ASN1EncodableVector vector = new ASN1EncodableVector();
		// 客户端身份认证
		vector.add(KeyPurposeId.id_kp_clientAuth);
		// 安全电子邮件
		vector.add(KeyPurposeId.id_kp_emailProtection);
		return new DERSequence(vector);
	}

	/**
	 * 生成证书文件
	 *
	 * @param x509Certificate
	 *            X.509格式证书
	 * @param savePath
	 *            证书保存路径
	 * @throws CertificateEncodingException
	 * @throws IOException
	 * @author Cliven
	 * @date 2018-12-21 17:21:50
	 */
	public static void makeCertFile(X509Certificate x509Certificate, Path savePath)
			throws CertificateEncodingException, IOException {
		if (Files.exists(savePath)) {
			// 删除已存在文件
			Files.deleteIfExists(savePath);
		}
		// 创建文件
		Files.createFile(savePath);

		// 获取ASN.1编码的证书字节码
		byte[] asn1BinCert = x509Certificate.getEncoded();
		// 编码为BASE64 便于传输
		byte[] base64EncodedCert = Base64.encode(asn1BinCert);
		// 写入文件
		Files.write(savePath, base64EncodedCert);
	}

	public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException,
			OperatorCreationException, IOException, CertificateException {
		// 生成密钥生成器, 产生密钥对
		KeyPairGenerator keyPairGenerator = SM2KeyGenerateFactory.generator();
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// 证书签名实现类 附加了 SM3WITHSM2 和 PrivateKey
		JcaContentSignerBuilder jcaContentSB = new JcaContentSignerBuilder("SM3withSM2");
		jcaContentSB.setProvider(new BouncyCastleProvider());
		ContentSigner sigGen = jcaContentSB.build(keyPair.getPrivate());

		// 准备证书信息
		BigInteger sn = BigInteger.valueOf(System.currentTimeMillis());
		X500Name issuer = createX500Name("yan");
		X500Name subject = createX500Name("yan");
		Date notBefore = new Date();
		Date notAfter = new Date(System.currentTimeMillis() + 50 * 1000);
		PublicKey publickey = keyPair.getPublic();

		// 构造证书信息
		JcaX509v3CertificateBuilder jcaX509v3Cert = new JcaX509v3CertificateBuilder(issuer, sn, notBefore, notAfter,
				subject, publickey);
		jcaX509v3Cert.addExtension(Extension.keyUsage, false,
				new X509KeyUsage(X509KeyUsage.digitalSignature | X509KeyUsage.nonRepudiation));
		jcaX509v3Cert.addExtension(Extension.extendedKeyUsage, false, extendedKeyUsage());
		jcaX509v3Cert.addExtension(MiscObjectIdentifiers.netscapeCertType, false,
				new NetscapeCertType(NetscapeCertType.sslClient));

		// 构造X.509 第3版的证书构建者
		X509v3CertificateBuilder x509v3Cert = jcaX509v3Cert;

		// 将证书构造参数装换为X.509证书对象
		X509Certificate certificate = new JcaX509CertificateConverter().setProvider(BC)
				.getCertificate(x509v3Cert.build(sigGen));

		// 保存为证书文件
		makeCertFile(certificate, Paths.get("sm2_jy.cer"));
		System.out.println("gen cert succ!");
	}

	// 构造 主题名称
	public static X500Name createX500Name(String cn) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		// 国家代码
		builder.addRDN(BCStyle.C, "CN");
		// 组织
		builder.addRDN(BCStyle.O, "info");
		// 省份
		builder.addRDN(BCStyle.ST, "beijing");
		// 地区
		builder.addRDN(BCStyle.L, "beijing");
		// 身份
		builder.addRDN(BCStyle.CN, cn);

		X500Name subject = builder.build();

		return subject;
	}

}
