package top.yancc.cert.pkcs10;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import cn.com.infosec.asn1.ASN1InputStream;
import cn.com.infosec.asn1.ASN1Sequence;
import cn.com.infosec.asn1.pkcs.CertificationRequest;
import cn.com.infosec.asn1.pkcs.CertificationRequestInfo;
import cn.com.infosec.asn1.x509.SubjectPublicKeyInfo;
import cn.com.infosec.util.Base64;

public class ParseCSRController {

	private final static String CERTIFICATE_REQ_BEGIN = "-----BEGIN NEW CERTIFICATE REQUEST-----",
			CERTIFICATE_REQ_END = "-----END NEW CERTIFICATE REQUEST-----",
			CERTIFICATE_REQ_BEGIN_1 = "-----BEGIN CERTIFICATE REQUEST-----",
			CERTIFICATE_REQ_END_1 = "-----END CERTIFICATE REQUEST-----";

	/**
	 * 解析 CSR,获取 主题 和 公钥
	 * 
	 * @param csr
	 * @return
	 * @throws IOException 
	 */
	public static String[] getPublicKey(String csr) throws Exception {
		String[] csr_info = new String[2];
		String publickey = null;

		byte[] p10data = decode(csr);
		ByteArrayInputStream bis = new ByteArrayInputStream(p10data);
		ASN1InputStream din = new ASN1InputStream(bis);
		bis.close();
		ASN1Sequence derRep = null;
		derRep = (ASN1Sequence) din.readObject();
		CertificationRequest c1 = new CertificationRequest(derRep);
		CertificationRequestInfo cinfo = c1.getCertificationRequestInfo();
		SubjectPublicKeyInfo kinfo = cinfo.getSubjectPublicKeyInfo();

		PublicKey pk = genPublicKey(kinfo);

		// publickey = "04" + bytesToHex(((JCESM2PublicKey) pk).getX()) +
		// bytesToHex(((JCESM2PublicKey) pk).getY());
		byte[] publicKey_byte = pk.getEncoded();
		publickey = bytesToHex(publicKey_byte);

		csr_info[0] = cinfo.getSubject().toString();
		csr_info[1] = publickey;

		return csr_info;
	}

	private static byte[] decode(String csr) throws IOException {
		csr = csr.replace(CERTIFICATE_REQ_BEGIN, "").replace(CERTIFICATE_REQ_BEGIN_1, "")
				.replace(CERTIFICATE_REQ_END, "").replace(CERTIFICATE_REQ_END_1, "").replaceAll("\\s", "");
		return Base64.decode(csr);
	}

	private static PublicKey genPublicKey(SubjectPublicKeyInfo kinfo) throws Exception {
		String pubkeyalg = kinfo.getAlgorithmId().getObjectId().getId();
		KeyFactory kf = null;
		X509EncodedKeySpec eks = new X509EncodedKeySpec(kinfo.getEncoded());
		if ("1.2.840.10045.2.1".equals(pubkeyalg)) {
			kf = KeyFactory.getInstance("EC", "INFOSEC");
		} else {
			throw new NoSuchAlgorithmException("Public Key getAlgorithmId not support (" + pubkeyalg + ").");
		}
		return kf.generatePublic(eks);
	}

	public static String bytesToHex(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			String s = Integer.toHexString(b & 0xFF);
			if (s.length() < 2) {
				s = "0" + s;
			}
			sb.append(s);
		}
		return sb.toString();
	}

}
