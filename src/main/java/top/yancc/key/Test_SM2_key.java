package top.yancc.key;

import java.io.IOException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;

import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.netcert.framework.crypto.CryptoException;
import cn.com.infosec.netcert.framework.crypto.IHSM;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.CertContainer;
import cn.com.infosec.netcert.framework.crypto.impl.ukey.UsbKeySKFImpl;
import cn.com.infosec.netcert.framework.log.FileLogger;

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
	
	private FileLogger log = FileLogger.getLogger(this.getClass());
	
	public static void main(String[] args) throws CryptoException, CertificateEncodingException, IOException {
		Security.addProvider(new InfosecProvider());
		
		// 加载驱动文件
		// 赢达信 C:\\Windows\\System32\\WTSKFInterface.dll 
		// 龙脉   /opt/jy/0__driver/libgm3000.1.0.so
		// 德安 D:\2__Program\5__Key_Tools\1__德安__key\智能密码钥匙V2.3版本动态库\智能密码钥匙V2.3版本动态库\win_v2.1_release_64
		String devFile = "C:\\Windows\\System32\\arguskey_csp11.dll";
		devFile = "C:\\Windows\\System32\\WTSKFInterface.dll";
		//devFile = "D:\\mtoken_gm3000_x64_anke.dll";
		//devFile = "D:\\ISSKFAPI31001_x64_anke.dll";
		
		// 遍历设备号
		String[] dev = UsbKeySKFImpl.enumDev(devFile);
		for (String s : dev) {
			System.out.println(s);
		}
		
		
		test_export_cert();
		
		/*
		UsbKeySKFImpl usbSkf = new UsbKeySKFImpl(devFile,"0001010674843E91");
		usbSkf.verifyPIN("123456");
		String[] csr = usbSkf.genCSR(256, new X509Name("cn=test"));
		for (String s : csr) {
			System.out.println(s);
		}
		*/
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
	
	
	/**
	 *     现象：admin 使用赢达信的key 登录，在错误日志有一条记录：
	 * [cn.com.infosec.netcert.framework.crypto.impl.ukey.UsbKeySKFImpl] SKF_ExportCertificate[key:89B97AC132383239] error: ret=0xa00001c
	 *     测试赢达信导出证书，
	 * @throws CryptoException 
	 */
	public static void test_export_cert() throws CryptoException {
		
		
		// 赢达信  0001010674843E91
		System.out.println("赢达信");
		UsbKeySKFImpl usbSkf_y = new UsbKeySKFImpl("C:\\\\Windows\\\\System32\\\\WTSKFInterface.dll","89B97BD169563239");
		CertContainer[] certs_y = usbSkf_y.enumCert(IHSM.SIGN);
		System.out.println("证书数量为： " + certs_y.length);
		usbSkf_y.release();
		
		// 龙脉
		System.out.println("龙脉");
		UsbKeySKFImpl usbSkf_l = new UsbKeySKFImpl("D:\\mtoken_gm3000_x64_anke.dll","DE75EB3666C5700316A123F2AC3C00A");
		CertContainer[] certs_l = usbSkf_l.enumCert(IHSM.SIGN);
		System.out.println("证书数量为： " + certs_l.length);
		usbSkf_l.release();
		
		// 海泰  3CA944211889480C
		System.out.println("海泰"); // 
		UsbKeySKFImpl usbSkf_h = new UsbKeySKFImpl("D:\\ISSKFAPI31001_x64_anke.dll","3CA944211889480C");
		CertContainer[] certs_h = usbSkf_h.enumCert(IHSM.SIGN);
		System.out.println("证书数量为： " + certs_h.length);
		usbSkf_h.release();
		
		
	}

}






