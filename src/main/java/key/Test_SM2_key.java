package key;

import java.io.IOException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;

import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.netcert.framework.crypto.CryptoException;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.UsbKeySKFImpl;

/**
 * 测试 SM2 的 key
 * @author Infosec_jy
 *
 * 1. 遍历 key 的设备号
 * 2. 通过驱动文件 和 设备号 创建对象实例
 * 3. 导出签名证书
 * 
 */
public class Test_SM2_key {

	public static void main(String[] args) throws CryptoException, CertificateEncodingException, IOException {
		Security.addProvider(new InfosecProvider());
		
		// 加载驱动文件
		String devFile = "C:\\Windows\\System32\\WTSKFInterface.dll";
		devFile = "/opt/jy/0__driver/libgm3000.1.0.so";
		
		// 遍历设备号
		String[] dev = UsbKeySKFImpl.enumDev(devFile);
		for (String s : dev) {
			System.out.println(s);
		}
		
		/*
		// 通过 驱动文件 和 设备号，创建对象实例
		UsbKeySKFImpl usbSkf = new UsbKeySKFImpl(devFile,"89B97BD169563239");
		//UsbKeySKFImpl usbSkf = new UsbKeySKFImpl("WTSKFInterface.dll","89B97BD169563239");

		// 主动输入 PIN ，过验证
		usbSkf.verifyPIN("88888888");
		
		// 导出签名证书,并保存到文件中
		CertContainer[] certs = usbSkf.enumCert(IHSM.SIGN);
		System.out.println("证书数量为： " + certs.length);
		byte[] cert_byte = Base64.decode(certs[1].getCert().toString());
		File file = new File("./test_sm2_0610.cer");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(cert_byte);
		fos.close();
		*/
		
		
		/* 遍历证书存放的容器名称
		for (CertContainer cert : certs) {
			System.out.println(cert.getContainerName());
		}
		*/
		// 产生 P10 
		/*
		String[] csr = usbSkf.genCSR(256, new X509Name("cn=test"));
		for (String s : csr) {
			System.out.println(s);
		}
		*/
		
		
	}

}






