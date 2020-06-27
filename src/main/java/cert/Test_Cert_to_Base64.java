package cert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.util.Base64;

/**   
 * @Description Cert ת Base64�ַ��� 
 * @Author ����    
 * @Time 2019-07-18 13:57
 */
public class Test_Cert_to_Base64 {

	/**       
	 * @Author ���� 
	 * @Time   2019-07-18 13:57
	 * @version 1.0 
	 * @throws NoSuchProviderException 
	 * @throws CertificateException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws CertificateException, NoSuchProviderException, IOException {
		Security.addProvider(new InfosecProvider());
		
		// �ļ�
		//String cert = "D:/cert.p7b";
		String cert = "E:/1_Project/1_Eclipse/CA_Admin/caServer_50/cert/ca.cer";
		
		// �ļ�  ת  String 
		FileInputStream fin_1 = new FileInputStream(new File(cert));
		byte[] bytes = new byte[fin_1.available()];
		fin_1.read(bytes);
		fin_1.close();
		
		String str = Base64.encode(bytes);
		System.out.println(str.length());
		System.out.println("֤���ļ�:"+str);
		
		// �ļ�   ת  ֤�� Cert
		FileInputStream fin_2 = new FileInputStream(new File(cert));
		CertificateFactory cf = CertificateFactory.getInstance("X.509", "INFOSEC");
		Certificate cer_2 = cf.generateCertificate(fin_2);
		fin_2.close();

		// ֤��Cert  ת  String 
		String cerB64 = Base64.encode(cer_2.getEncoded());
		System.out.println(cerB64.length());
		System.out.println("cert��String��"+cerB64);
		

		CertificateFactory cf_1 = CertificateFactory.getInstance("X.509", "INFOSEC");
		X509Certificate signCer = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(Base64.decode(cerB64)));
		
	}

}





