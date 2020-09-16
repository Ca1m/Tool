package top.yancc.rads;

import cn.com.infosec.netcert.framework.*;
import cn.com.infosec.netcert.framework.resource.*;
import cn.com.infosec.netcert.rads61.*;
import java.security.Security;
import java.security.cert.*;
import java.io.*;
import java.util.*;

public class Test_R_D_101 {

	static SysProperty sysproperty = new SysProperty();
	static Random r = new Random();
	
	public static void main(String[] args) throws Throwable {
		init();
		action();
	}
	
	
	public static int init() throws Throwable {
		Security.addProvider(new cn.com.infosec.jce.provider.InfosecProvider());

		sysproperty.setTransIP("10.20.86.101");
		sysproperty.setTransPort(22342);
		sysproperty.setProtocolName("XML");

		sysproperty.setSignAlgName("SM3withSM2");
		Certificate cert = CertificateFactory.getInstance("X.509", "INFOSEC")
				.generateCertificate(new FileInputStream("./cert/10.20.86.101/ra_hsm_101_01.cer"));
		sysproperty.setSignCert(cert);
		sysproperty.setChanelEncryptName("plain_pool");
		sysproperty.setMaxConnection(200);
		// sysproperty.setAnonymousClazz("cn.com.infosec.netcert.rads61.NullOp");
		// CertManager.setAnonymousSysProperty(sysproperty);

		// sysproperty.setHsmName("");
		// sysproperty.setKeyIdx("D:\\jiaoben\\CA_63_SM2_jy\\10.20.83.245\\ra_0528_sm2");
		// sysproperty.setPwd("123456");

		sysproperty.setHsmName("三未信安SJJ1012");
		sysproperty.setKeyIdx("5");
		sysproperty.setPwd("12345678");

		sysproperty.setMaxConnection(100);
		CertManager.setSysProperty(sysproperty);

		return 0;

	}// end of init

	public static int action() throws Throwable {
		// NullOp cm = (NullOp)CertManager.getAnonymousInstance();
		CertManager manager = CertManager.getInstance();
		int random = r.nextInt();
		String uIdtxt = String.valueOf(System.currentTimeMillis()) + "_" + String.valueOf(random);
		String template = "ee_sign";
		String subjectDN = "cn=jzj001_" + uIdtxt + ",c=cn";
		String validTime = "30";
		String uuid = uIdtxt;

		// SM2
		String publiKey = "MIHUMHwCAQAwHDENMAsGA1UEAwwEcmFkczELMAkGA1UECgwCcmEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAATp5Muq0pTXV6apJdJN/2OmAi+UNUwKX5iuQxkvv8gX/NMirJ69WHOlfzxdgO10cQcLScWB6pRQnRUeIEyH7l2aMAoGCCqBHM9VAYN1A0gAMEUCIQDkbTyJGamM620I/jZUHk/1jVCCjWLInORlYkkWFNHqagIgGB/oOtNDaKkbx7Jc2+hFj3wxE6MqeMCeN30Z4Vhroiw=";
		String tmpPubKey = "";

		// RSA
		// String publiKey =
		// "MIIBXjCBygIBADAhMRIwEAYDVQQDDAlyYWRzXzAzMzExCzAJBgNVBAoMAnJhMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLUz9llAjagtGi/ynY+MiasjGJke0oYtWUe3/dliUhc7Wo6AA9soHkMdyjh6xU8XCw2AGnLWF/Z/e5r8fYL1AQVjtZ/q3TVVBCHFmD/n+PmGGxBGgaDzwCSkBvLdCCwplf8RoBWWcUsG4E1gPK120EwY6tCk3tHK4R3Pz6y5u5swIDAQABoAAwCwYJKoZIhvcNAQEFA4GBAIVCRkPw4EqMvIGaROmK+dfVMf9e9YYvQGh7o2eckV0qapn0ECydUtspEKuYwW7X48ccgihH3vI9xulaODLe/KsKkb/gaddLk6cN/9kEhYeBctX0DONgu/XabO9wSdFGxiUaSRBulXIwdjHV3vo+X/OOISfLZb6Gi86hpHRuB5sd";
		// String tmpPubKey =
		// "MIGJAoGBAKSOppdCXPqSs/Is6oHIe+OQtmldUMygBD2+gDn73uJMDBib+NV4Qqu2Zeaw/BMylVLcHh6qrPuzT390f3sJKoGfwDzmV2ytT+LlXTSKYCaM/1z+AYEHH+vaB+meC6nZ24SWOP3Dn0/jyQXb6odtWAkr7xx24OOrSvcVUdQZfIH7AgMBAAE=";

		Properties p = new Properties();
		p.setProperty(PropertiesKeysRes.TEMPLATENAME, template);
		p.setProperty(PropertiesKeysRes.SUBJECTDN, subjectDN);
		p.setProperty(PropertiesKeysRes.VALIDITYLEN, validTime);
		p.setProperty(PropertiesKeysRes.UUID, uuid);
		p.setProperty(PropertiesKeysRes.PUBLICKEY, publiKey);
		if (tmpPubKey != null && tmpPubKey.length() > 0) {
			p.setProperty(PropertiesKeysRes.RSA_TMP_PUB_KEY, tmpPubKey);
			p.setProperty(PropertiesKeysRes.KMC_KEYLEN, "1024");
			p.setProperty(PropertiesKeysRes.RETSYMALG, "RC4");
			p.setProperty(PropertiesKeysRes.RETURNTYPE, "P7CERT");
		} else {
			p.setProperty(PropertiesKeysRes.KMC_KEYLEN, "256");
			p.setProperty(PropertiesKeysRes.RETSYMALG, "SM1");
			p.setProperty(PropertiesKeysRes.RETURNTYPE, "CERT");
		}
		Properties pro = manager.requestAndDown(p, null);
		String p7 = pro.getProperty(PropertiesKeysRes.P7DATA, "");
		String encCer = pro.getProperty(PropertiesKeysRes.P7DATA_ENC, "");
		String encPri = pro.getProperty(PropertiesKeysRes.ENCPRIVATEKEY, "");
		String ukek = pro.getProperty(PropertiesKeysRes.TEMPUKEK, "");
		return 0;
	}// end of action

	public int end() throws Throwable {
		return 0;
	}// end of end
}
