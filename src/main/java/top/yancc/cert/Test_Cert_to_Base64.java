package top.yancc.cert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
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

/**   
 * @Description Cert  ===  Base64(String)
 * @Author jy
 * @Time 2019-07-18 13:57
 */
public class Test_Cert_to_Base64 {

	/**        
	 * @Time   2019-07-18 13:57
	 * @version 1.0 
	 * @throws NoSuchProviderException 
	 * @throws CertificateException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws CertificateException, NoSuchProviderException, IOException {
		Security.addProvider(new InfosecProvider());
		
		Encoder encoder = Base64.getEncoder();
		Decoder decoder = Base64.getDecoder();
		
		
		//String cert = "D:/cert.p7b";
		String cert = "E:/1_Project/1_Eclipse/CA_Admin/caServer_50/cert/ca.cer";
		
		//  String 
		FileInputStream fin_1 = new FileInputStream(new File(cert));
		byte[] bytes = new byte[fin_1.available()];
		fin_1.read(bytes);
		fin_1.close();
		
		
		String encode_Cert = encoder.encodeToString(bytes);
		System.out.println(encode_Cert.length());
		System.out.println("证书文件的Base64编码:"+encode_Cert);
		
		// string  to Cert
		FileInputStream fin_2 = new FileInputStream(new File(cert));
		CertificateFactory cf = CertificateFactory.getInstance("X.509", "INFOSEC");
		Certificate cer_2 = cf.generateCertificate(fin_2);
		fin_2.close();

		// cert  to  string
		String cerB64 = encoder.encodeToString(cer_2.getEncoded());
		System.out.println(cerB64.length());
		System.out.println("cert的String"+ cerB64);
		

		CertificateFactory cf_1 = CertificateFactory.getInstance("X.509", "INFOSEC");
		X509Certificate signCer = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(decoder.decode(cerB64)));
		
		
	
		
	}

}





