package utils;

import cn.com.infosec.netcert.ca.sync.CertInfoBuilder;

public class Test_long {

	public static void main(String[] args) {
		
		
		
		
		CertInfoBuilder.CertInfoBean.Builder bb = CertInfoBuilder.CertInfoBean.newBuilder();
		
		String ss = "20200718120258Z";
		
		bb.setNotAfter(Long.parseLong(ss));
		
	}

}
