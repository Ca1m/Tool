package key;

import cn.com.infosec.netcert.framework.crypto.CryptoException;
import cn.com.infosec.netcert.framework.crypto.IHSM;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.CertContainer;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.CspCertInfo;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.UsbKeyCSPImpl;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.UsbKeySKFImpl;

public class Test_rsa_export_cert {

	public static void main(String[] args) throws CryptoException {
		
		//test_RSA_exportCert();
		
		test_SM2_exportCert();

		
		
	}

	public static void test_SM2_exportCert() {
		
		
	
		
		
	}
	public static void test_RSA_exportCert() throws CryptoException {
		
		String[] providers = UsbKeyCSPImpl.listProvider();
		for (String s : providers) {
			System.out.println(s);
		}
		
		UsbKeyCSPImpl userCSP = new UsbKeyCSPImpl("Microsoft Base Cryptographic Provider v1.0");
		CspCertInfo[] cspList = userCSP.listCert(IHSM.SIGN);
		
		for(CspCertInfo csp : cspList) {
			if(csp.container.equalsIgnoreCase("F5B03037-D14E-426A-B823-97C7EDE991D5")) {
				System.out.println(csp.container + "," + csp.subject);
			}
		}
		
		String signCerStr = userCSP.exportCertBase64(IHSM.SIGN, "F5B03037-D14E-426A-B823-97C7EDE991D5");
		String encCerStr = userCSP.exportCertBase64(IHSM.ENC, "F5B03037-D14E-426A-B823-97C7EDE991D5");
	}
	
	
}
