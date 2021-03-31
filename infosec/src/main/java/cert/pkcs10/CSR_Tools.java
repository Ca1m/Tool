package cert.pkcs10;

import cn.com.infosec.asn1.x509.X509Name;
import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.netcert.framework.crypto.HSM;
import cn.com.infosec.netcert.framework.crypto.IHSM;
import cn.com.infosec.netcert.framework.crypto.Pkcs10;
import cn.com.infosec.netcert.framework.log.FileLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Security;


/**
 * CSR 工具类
 * 
 * @author Infosec_jy
 *
 *  1. 产生P10 2. 解析P10
 */
public class CSR_Tools {

	private FileLogger log = FileLogger.getLogger(this.getClass());
	
	
	public static void main(String[] args) throws Exception {
		Security.addProvider(new InfosecProvider());

		CSR_Tools csr_Tools = new CSR_Tools();
		
		// 产生 CSR ： SM2  或者 RSA
		String name = "rads_08";
		
		String[] args_sm2 = new String[] { "cn=ra_0825_sm2", "SOFT", "ra_0825_sm2", "256", "SM3withSM2", "123456", "./ra/" + name, "1234567812345678" };
		String[] args_rsa = new String[] { "cn=ra_0827_rsa_22", "SOFT", "ra_0827_rsa_22", "2048", "SHA256withRSA", "123456", "./ra/" + name};
		
		//csr_Tools.exportCSR(args_rsa);
		
		String csr_0821_sm2 = "-----BEGIN NEW CERTIFICATE REQUEST-----\r\n" + 
				"MIHJMG8CAQAwDzENMAsGA1UEAwwEdGVzdDBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABJ5JTUj41OEEJM171rpWXbLfmy/lSHcy92CRD/EaK8fBxvISPSkOtztOmikBqnz+74gBifFwHL9xqfjeVMRWn28wDAYIKoEcz1UBg3UFAANIADBFAiBZ8lhvzO3MwpgUj2+DpQ2xdavS6eu1q9iNz4T1oyRPegIhAOJ3J/V2GLuZToqIupvP7WornLJMz3poHiTPm3xBAwOO\r\n" + 
				"-----END NEW CERTIFICATE REQUEST-----";
		
		
		String csr_0821_rsa = "-----BEGIN NEW CERTIFICATE REQUEST-----\r\n" + 
				"MIIBVTCBwQIBADAYMRYwFAYDVQQDDA1yYWRzXzA4MjFfcnNhMIGfMA0GCSqGSIb3\r\n" + 
				"DQEBAQUAA4GNADCBiQKBgQCP3HtZaERSbbMVk1SLLzFxQOI7zGaPWH3noJT3kYsB\r\n" + 
				"JQH2+FMkCUuYoKw4UauqIAFf1JFOiyrFTFmbqjoH0hrZeuQdIViQKO+mzE+5BGIz\r\n" + 
				"nobJlM8VdkZSURa9LE1iw+uKHO59hkESqnXLr3jM9d7uDCXO91HlFBsoqXAnc3T7\r\n" + 
				"/wIDAQABoAAwCwYJKoZIhvcNAQEFA4GBAGCHk1sjvZhAqbVXW8Je2rvYj8imzXKW\r\n" + 
				"UgLZT+uUb4yxn/nzicY3o0YIIwgSfkFgTqwY8g3GzuxgLg1zVlqAS6p/tCMriCEg\r\n" + 
				"+ELu6AkHs5j3dVkwTWPdoL0dfNcM/jsFlIONMHQNBhZ93W82sL8vtOWPrzHcbdTl\r\n" + 
				"mat7Ot1ncxK7\r\n" + 
				"-----END NEW CERTIFICATE REQUEST-----";
		
		parseCSR(csr_0821_sm2);

	}

	/**
	 *       产生CSR
	 * @param args
	 * @throws Exception
	 */
	public void exportCSR(String[] args) throws Exception {
		
		log.errlog("111111");
		
		String subject = args[0], hsmName = args[1], keyIdx = args[2], _keyLen = args[3], signAlg = args[4], pwd = args[5], path = args[6];
		String sm2Id = null;
		if (args.length == 8) {
			sm2Id = args[7];
		}
		int keyLen = Integer.parseInt(_keyLen);
		if (!path.endsWith(File.separator)) {
			path += File.separator;
		}
		
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
		
		String csrfile = path + keyIdx + ".csr";
		if (hsmName.equalsIgnoreCase("soft")) {
			hsmName = "";
			keyIdx = path + keyIdx;
		}
		IHSM hsm = HSM.getInst(hsmName);
		hsm.init();
		String _csr = Pkcs10.genCSR(new X509Name(subject), keyIdx, keyLen, signAlg, pwd.toCharArray(), hsm, sm2Id);
		hsm.release();
		FileOutputStream os = new FileOutputStream(csrfile);
		os.write(_csr.getBytes());
		os.close();

		System.out.println("产生CSR成功！");
		System.out.println("文件保存在：" + new File(path).getAbsolutePath());
	}

	
	/**
	 *     验证 CSR 格式，只能验证 SM2 
	 * @param p10
	 * @throws Exception
	 */
	public static void parseCSR(String p10) {
		try {
			String[] csr_info = ParseCSRController.getPublicKey(p10);
			System.out.println("主题信息：" + csr_info[0]);
			System.out.println("公钥信息：" + csr_info[1]);
		
			Pkcs10 pkcs10 = Pkcs10.parseCSR(p10, HSM.getInst(""));
			System.out.println("PKCS10 格式正确！");
		} catch (Exception e) {
			System.out.println("PKCS10 格式异常 或者 为 RSA 算法！");
			e.printStackTrace();
		}
	}

}


