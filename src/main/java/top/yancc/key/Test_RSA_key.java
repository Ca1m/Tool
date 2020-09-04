package top.yancc.key;

import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertificateException;

import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.netcert.framework.crypto.CryptoException;
import cn.com.infosec.netcert.framework.crypto.IHSM;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.CspCertInfo;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.UsbKeyCSPImpl;

/**
 * 测试 RSA 的 key 功能
 * @author Infosec_jy
 *
 * 1. 遍历所有的 Provider
 * 2. 遍历某个 Provider 下面的所有证书
 * 3. 从 key （Provider）中导出某个 容器下的证书（container），保存文件
 * 4. 产生 P10
 * 
 */
public class Test_RSA_key {
	
	public static void main(String[] args) throws NumberFormatException, CryptoException, CertificateException, NoSuchProviderException, IOException {
		Security.addProvider(new InfosecProvider());
		
		String[] provider = UsbKeyCSPImpl.listProvider();
		System.out.println("遍历所有的 CSP 的Provider:");
		for (String s : provider) {
			System.out.println(s);
		}
		
		// 创建CSP对象实例
		UsbKeyCSPImpl cspImpl = new UsbKeyCSPImpl("XASJ Cryptographic Service Provider 31001");
		// 遍历证书
		CspCertInfo[] cspCert = cspImpl.listCert(IHSM.SIGN);
		for (CspCertInfo cert : cspCert) {
			System.out.println(cert.container +","+ cert.subject);
		}
		
		/* 从key中导出证书 
		String cert = cspImpl.exportCertBase64(IHSM.SIGN, "01839E23-2B6A-40D7-ACD5-3526AC07F632");
		byte[] cert_byte = Base64.decode(cert);
		File file = new File("./test_rsa_0610.cer");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(cert_byte);
		fos.close();
		*/
		
		/* 产生 P10
		String[] csr = cspImpl.genP10(Integer.parseInt("1024"), "cn=test");
		for (String s : csr) {
			System.out.println(s);
		}
		*/
		
	}
	
	
	
}










