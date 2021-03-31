package cert;

import java.io.File;
import java.security.Security;
import java.security.cert.X509Certificate;
import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.netcert.framework.crypto.CipherUtil;


public class Test_cert_verify {

	public static void main(String[] args) throws Exception {
		Security.addProvider(new InfosecProvider());

		File caCert = new File("C:\\Users\\Infosec_jy\\Desktop\\ca63_test\\0__Env\\10.20.85.100\\caICBC.cer");

		File adminCert = new File("C:\\Users\\Infosec_jy\\Desktop\\ca63_test\\0__Env\\10.20.85.100\\icbcKMCBO.cer");
		// C:\\Users\\Infosec_jy\\Desktop\\ca63_test\\0__Env\\10.20.85.100\\adminCert.cer

		X509Certificate caCert_ = X509Certificate_utils.parse_Certificate(caCert);
		X509Certificate adminCert_ = X509Certificate_utils.parse_Certificate(adminCert);

		
		if (CipherUtil.certValid(adminCert_, caCert_) != 0) {
			System.out.println(CipherUtil.certValid(adminCert_, caCert_));
			System.out.println("验证失败！！！");
		}
		
	}
}



