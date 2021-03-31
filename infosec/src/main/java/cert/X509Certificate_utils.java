package cert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import cn.com.infosec.jce.provider.InfosecProvider;

public class X509Certificate_utils {

	public static void main(String[] args) throws Exception {
		
		Security.addProvider(new InfosecProvider());
		
		File file = new File("C:\\Users\\Infosec_jy\\Desktop\\ca63_test\\0__Env\\10.20.85.100\\caICBC.cer");
		X509Certificate cert_ = parse_Certificate(file);

		System.err.println(cert_.getSubjectDN());
		
	}

	// 文件 格式化为  X509Certificate
	public static X509Certificate parse_Certificate(File certFile) throws CertificateException, NoSuchProviderException, IOException {
		
		Encoder encoder = Base64.getEncoder();
		Decoder decoder = Base64.getDecoder();
		
		//String cert = "E:/1_Project/1_Eclipse/CA_Admin/caServer_50/cert/ca.cer";
		
		//  String 
		//FileInputStream fin_1 = new FileInputStream(new File(cert));
		//byte[] bytes = new byte[fin_1.available()];
		//fin_1.read(bytes);
		//fin_1.close();
		//String encode_Cert = encoder.encodeToString(bytes);
		//System.out.println(encode_Cert.length());
		//System.out.println("证书文件的Base64编码:"+encode_Cert);
		
		// string  to Cert
		FileInputStream fin_2 = new FileInputStream(certFile);
		CertificateFactory cf = CertificateFactory.getInstance("X.509", "INFOSEC");
		Certificate cer_2 = cf.generateCertificate(fin_2);
		fin_2.close();

		// cert  to  string
		String certBase64 = encoder.encodeToString(cer_2.getEncoded());
		//System.out.println(certBase64.length());
		//System.out.println("cert的String"+ certBase64);
		
		CertificateFactory cf_1 = CertificateFactory.getInstance("X.509", "INFOSEC");
		X509Certificate cert_ = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(decoder.decode(certBase64)));
		
		return cert_;
	}
	
	
	
}
