package cert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;

import cn.com.infosec.jce.provider.InfosecProvider;
import cn.com.infosec.netcert.framework.crypto.HSM;
import cn.com.infosec.netcert.framework.crypto.IHSM;
import cn.com.infosec.netcert.framework.crypto.SM2Id;
import cn.com.infosec.util.Base64;
import top.yancc.java.time.Test_time_2;

public class Test_VerifySign {

	public static void main(String[] args) throws Exception {
		Security.addProvider(new InfosecProvider());

		String reqSrc = "PHNvdXJjZT48aGVhZGVyPjxJRD4wNENFREE8L0lEPjxUQVNLVEFHPjQyNDc2PC9UQVNLVEFHPjxMT0NBTFRJTUU+MTU5MDQ4MTc2NzIxNDwvTE9DQUxUSU1FPjxWRVJTSU9OPjA8L1ZFUlNJT04+PFRZUEU+TE9HSU48L1RZUEU+PC9oZWFkZXI+PC9zb3VyY2U+";
		String reqSign = "eTRWnmnLNUzpXSTG2SaBzfNWgrPRzCJ0VXT7fwT1JaBsJswCUEitw2y0e/pEeeiR7qz12WsiTfzKwGKryUu1maWIxSx4V+VTguaL09TRQCUatVxT/ntFdyO/8AY6OssWHN+7AZEKMT/5xszLzJ+LLBiYk+vXeta3gLek4ciE7pk=";
		String reqSignAlg = "SHA1withRSA";
		String reqCert = "MIIC8DCCAdigAwIBAgIDBM7aMA0GCSqGSIb3DQEBBQUAMEMxCzAJBgNVBAYTAmNuMQwwCgYDVQQKDANzZWMxDTALBgNVBAsMBGluZm8xFzAVBgNVBAMMDmNhXzYzX3JzYV8wNDIwMB4XDTIwMDUyNjA4Mjg1N1oXDTIxMDUyNjA4Mjg1N1owVzELMAkGA1UEBhMCY24xDDAKBgNVBAoMA3NlYzENMAsGA1UECwwEaW5mbzEXMBUGA1UECwwOYWRtaW5pc3RyYXRvcnMxEjAQBgNVBAMMCWFvXzA1MjYwMTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAxE6YIeourZH3A9VN89t/+14F5qJZQr1Xq66lQWSvdY5Af6EjSznyi6hjQEdq/u+M4geJe4JdKg97Oplw+iuxJSm1qOA5heWmY5U06/ITUYHBqiaLfSueKh3ZmG1KfetStTAM82HMR4KDKsF7qBU7aoNsNYHKobuBiwhv5xOjdlMCAwEAAaNdMFswHwYDVR0jBBgwFoAURBr555VmO8JLCsoclj3m0lOS21MwCQYDVR0TBAIwADAOBgNVHQ8BAf8EBAMCB4AwHQYDVR0OBBYEFCrRZsPjn0dvjysgT7L2a+suG2X2MA0GCSqGSIb3DQEBBQUAA4IBAQAIshuxgLffk/Lc7oDPXIHF1GbChJ5HGRpn3HU0ikG5ynaf91tdapxxVHzJHHy30N+hfMGSEAlUAt98Mt23GHXGXB9Vj9gNesIfcmK8BaCtsbo2DuiMCGPR2pgq+SRtyHEmvX6Twi7FnNjWUEcDn0XXX9f8LLqpe1Hjtz7NtOba5DN5Snjc8iSUoPMXbNa2cCdjGlFGPuuSRzRErmoDZlAyN/yi//KC9g12kyZ1chqYTc2E2n6MO7qBghq+R5k5Z+9k2MrrWG2Lheif5euuLMxLNDCpF4i/fNED/wI9KhrgPX0wEwqPN09Mf0q/pA/URIFBEZrNaQ7vKzeE573mZqWx";

/*
		reqSrc = "PHNvdXJjZT48aGVhZGVyPjxWRVJTSU9OPjA8L1ZFUlNJT04+PFRZUEU+QVBQTFlVU0VSPC9UWVBFPjxMT0NBTFRJTUU+MTU5NDcyMzM0MTEyOTwvTE9DQUxUSU1FPjxUQVNLVEFHPjYyMzM5PC9UQVNLVEFHPjxJRD48L0lEPjwvaGVhZGVyPjxib2R5PjxlbnRyeSBuYW1lPSJVU0VSTkFNRSI+PCFbQ0RBVEFbcHBdXT48L2VudHJ5PjwvYm9keT48L3NvdXJjZT4=";
		reqSign = "MEUCIQDsw/oAcPgVIaYRuXN0vPOncMUsf1GzlyF7gwt/TtEaKQIgOdNZyR7i+keTLi11AF39dtP53bKmHqIu8KEtKkCgi8Q=";
		reqSignAlg = "SM3withSM2";
		reqCert ="MIIB2DCCAXygAwIBAgIDEZ5yMAwGCCqBHM9VAYN1BQAwQDELMAkGA1UEBhMCY24xEDAOBgNVBAoMB2luZm9zZWMxDjAMBgNVBAsMBWJ1bWVuMQ8wDQYDVQQDDAZyb290Y2EwHhcNMjAwNzE0MDkzMjEwWhcNMjEwNzE0MDkzMjEwWjBJMQswCQYDVQQGEwJjbjEQMA4GA1UECgwHaW5mb3NlYzEXMBUGA1UECwwOYWRtaW5pc3RyYXRvcnMxDzANBgNVBAMMBlJBNjExMTBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABCHeQEguX4b1Xc5UrMWDUL3oWsRWX94ogTGLXYw4RnqY5qr1YKANMKoimWqsr2MuM8XKwo2LVItS6vLE5y0EBUKjWjBYMB8GA1UdIwQYMBaAFM2vnwysSuI8fRgs+eNmMCPH2o/9MAkGA1UdEwQCMAAwCwYDVR0PBAQDAgeAMB0GA1UdDgQWBBStYx7r/vsiSgHD1rCGy9jXwLSICzAMBggqgRzPVQGDdQUAA0gAMEUCIGGjGuVcPCYqLiJWbgnohgpfx+qsutaHsbK+HbvN2uoqAiEA4DzlIjgwbfWpQLDi/p+G260jJFEQtoOekyi9zDD79mU=";
*/
		
		//getCertInfo(reqCert); // 获取证书信息

		verifySign(reqSrc, reqSign, reqSignAlg, reqCert); // 验证签名值

	}

	/**
	 * 从Base64的证书字符串中获取 各项数据
	 * @param certFile
	 * @param certStr
	 * @throws ParseException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchProviderException
	 */
	private static void getCertInfo(String certStr) throws Exception {

		byte[] cerBin = Base64.decode(certStr); // 解析 Base64
		CertificateFactory cf = CertificateFactory.getInstance("X.509", "INFOSEC");
		X509Certificate c = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(cerBin));

		System.out.println("SerialNumber:" + c.getSerialNumber());
		System.out.println("subjectDN:" + c.getSubjectDN());
		System.out.println("Issuer:" + c.getIssuerDN());
		System.out.println("Pubkey:" + Base64.encode(c.getPublicKey().getEncoded()));
		System.out.println("notBefore:" + Test_time_2.format(c.getNotBefore()));
		System.out.println("notAfter:" + Test_time_2.format(c.getNotAfter()));
	}

	/**
	 * 签名验签
	 * @param reqSrc
	 * @param reqSign
	 * @param reqSignAlg
	 * @param reqCert
	 * @throws IOException 
	 * @throws Exception 
	 */
	private static void verifySign(String reqSrc, String reqSign, String reqSignAlg, String reqCert) throws Exception {
		byte[] cerBin = Base64.decode(reqCert);
		CertificateFactory cf = CertificateFactory.getInstance("X.509", "INFOSEC");
		X509Certificate c = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(cerBin));

		PublicKey pubkey = c.getPublicKey();
		IHSM hsm = HSM.getInst("");
		if (hsm.verify(Base64.decode(reqSrc), Base64.decode(reqSign), pubkey, reqSignAlg,
				SM2Id.getVerifyId("LOGAUDIT"))) {
			System.out.println("success!!!!");
		} else {
			System.out.println("fail!!!!");
		}
	}

	/**
	 * 测试编码字符集
	 * @throws Exception
	 */
	private static void test_encode() throws Exception {
		String reqSrc = "PHNvdXJjZT48aGVhZGVyPjxJRD4wMEI3OUFDRDwvSUQ+PFRBU0tUQUc+OTAxNzY8L1RBU0tUQUc+PExPQ0FMVElNRT4xNTkwNDgxOTQzNTg5PC9MT0NBTFRJTUU+PFZFUlNJT04+MDwvVkVSU0lPTj48VFlQRT5SRUdJU1RFUkFPPC9UWVBFPjwvaGVhZGVyPjxib2R5PjxlbnRyeSBuYW1lPSJFTUFJTCI+PCFbQ0RBVEFbIF1dPjwvZW50cnk+PGVudHJ5IG5hbWU9IlJFTUFSSyI+PCFbQ0RBVEFbIF1dPjwvZW50cnk+PGVudHJ5IG5hbWU9IlVTRVJOQU1FIj48IVtDREFUQVu077Tzz8NdXT48L2VudHJ5PjxlbnRyeSBuYW1lPSJURUxFUEhPTkUiPjwhW0NEQVRBWyBdXT48L2VudHJ5PjxlbnRyeSBuYW1lPSJWQUxJRElUWUxFTiI+PCFbQ0RBVEFbMV1dPjwvZW50cnk+PGVudHJ5IG5hbWU9IlZBTElESVRZTEVOX1VOSVQiPjwhW0NEQVRBW3ldXT48L2VudHJ5PjwvYm9keT48L3NvdXJjZT4=";
		String reqSign = "D8aOwW/yyxpkvNz5qHZ739obSxAPg66fsJ/ckwBe0P8BHjLJNVoedS6wxXTHl3R5lr1IjyTdpzSHf+3hGJvT88Yp7asxFYNUGlJ0tWauwCqdkqCjXyoEXWCbxXOI80QZZZJEnImzFxT5rHIZmrz9k3Od43aaqDhmbCp1Fb3/Kg8=";
		String reqSignAlg = "SHA1withRSA";
		String reqCert = "MIIC6jCCAdKgAwIBAgIEALeazTANBgkqhkiG9w0BAQUFADBDMQswCQYDVQQGEwJjbjEMMAoGA1UECgwDc2VjMQ0wCwYDVQQLDARpbmZvMRcwFQYDVQQDDA5jYV82M19yc2FfMDQyMDAeFw0yMDA0MjAwMzQwMzhaFw0zMDAyMjcwMzQwMzhaMFAxCzAJBgNVBAYTAmNuMQwwCgYDVQQKDANzZWMxDTALBgNVBAsMBGluZm8xFzAVBgNVBAsMDmFkbWluaXN0cmF0b3JzMQswCQYDVQQDDAJBQTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA0RvA1U5KLFfJOh9wDa7Rvllq0PqpgQJBq82KkIs2TTSS3FF2mYsEFXDh2vFZfwB8zDtOrcia2nMB9/DPmQyE6pN1kKvvn19xWiB91vC+ciQWnHshYPyRgqRqZwoVSu5BurJWJqyV/wy92ipRBYP3b8pS3REcZJdJJPcAzOnc4SkCAwEAAaNdMFswHwYDVR0jBBgwFoAURBr555VmO8JLCsoclj3m0lOS21MwCQYDVR0TBAIwADAOBgNVHQ8BAf8EBAMCB4AwHQYDVR0OBBYEFBoyZ5fSxkjelUwNVP6MTCmGWjUbMA0GCSqGSIb3DQEBBQUAA4IBAQAiJuYQTlDdHwGjc9KsnzEgbtGbD9PipAyaLzbjNNP0Bg5q4DI0MXC/yFUe2GHOOmjP/tooWbgWMF41C5GcEDZXChoZUS9v9XnrWRZmSn9CAO/73cjfFvyKe36KLMMIXMGs4qlpIw2nDv6SjxHKZpbftWaL2mSmyVB0ng5VWZfxkeKw5/NHqabqB6nJlXReMTiRhar8z0gd3Z6QLEsWgSm55r7tz7Umv8N+yKS5W7dES+VLngn8k0pwDYmOYZgYNysAtbtIOPT9VMa8MCxjYTl8NHk2yRLnaACkisNlDN8slQbAmTjg1AvWZkO/X7N7NIzy3BqOShJxVY7i+x4PA63r";

		String text = "<source><header><ID>00B79ACD</ID><TASKTAG>90176</TASKTAG><LOCALTIME>1590481943589</LOCALTIME><VERSION>0</VERSION><TYPE>REGISTERAO</TYPE></header><body><entry name=\"EMAIL\"><![CDATA[ ]]></entry><entry name=\"REMARK\"><![CDATA[ ]]></entry><entry name=\"USERNAME\"><![CDATA[达大厦]]></entry><entry name=\"TELEPHONE\"><![CDATA[ ]]></entry><entry name=\"VALIDITYLEN\"><![CDATA[1]]></entry><entry name=\"VALIDITYLEN_UNIT\"><![CDATA[y]]></entry></body></source>";
		byte[] bs = text.getBytes("UTF-8");
		String reqSrc_ = Base64.encode(bs);
		System.out.println("从文本框获取的：" + reqSrc_);

		byte[] byte_src = Base64.decode(reqSrc);
		System.out.println("填入文本框获取：" + new String(byte_src, "UTF-8"));
		System.out.println("从服务器获取的：" + reqSrc);
	}

}
