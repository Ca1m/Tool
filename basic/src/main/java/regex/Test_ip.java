package regex;

public class Test_ip {

	public static void main(String[] args) {//142
		boolean inIpRange = inIpRange("255.1.13.13", "10.20.61.138/28");
		System.out.println(inIpRange);
	}
		
	public static boolean inIpRange(String ip, String cidr) {
		if("".equals(cidr) || null==cidr) {
			return false;			
		}
		if(!cidr.contains("/")) {//只填ip，默认只允许该ip访问
			cidr += "/32";
		}
		
		String[] ipTests = ip.split("\\.");
		System.out.println(Integer.valueOf(ipTests[2]));
		System.out.println(Integer.valueOf(ipTests[3]));
		
		System.out.println(ipTests[1].length());
		System.out.println(ipTests[2].length());
		System.out.println(ipTests[3].length());
		
		int ipTest = (Integer.valueOf(ipTests[0]) << 24)
				| (Integer.valueOf(ipTests[1]) << 16)
				| (Integer.valueOf(ipTests[2]) << 8)
				| Integer.valueOf(ipTests[3]);//计算ip
		
		int mask = 0xFFFFFFFF << (32 - Integer.valueOf(cidr.split("/")[1]));//计算子网掩码
		
		String[] iPsrcs = cidr.split("/")[0].split("\\.");
		int iPsrc = (Integer.valueOf(iPsrcs[0]) << 24)
				| (Integer.valueOf(iPsrcs[1]) << 16)
				| (Integer.valueOf(iPsrcs[2]) << 8)
				| Integer.valueOf(iPsrcs[3]);//计算ip
		
		return (ipTest & mask) == (iPsrc & mask);
	}
	
	

}
