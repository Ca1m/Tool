package top.yancc.cert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**  
 * @Description
 * @Author jy    
 * @Time 2019-07-17 20:52
 */
public class Test_SN_HEX {
	/**       
	 * @Author jy 
	 * @Time   2019-07-17 20:52
	 * @version 1.0 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
		String sn = "9510764";
		String hex = "00f29af7";
		
		// BigDecimal  to  SN
		String SN = new BigDecimal("268435524").toBigInteger().toString(16);
		System.out.println(SN);

		// SN  to  BigDecimal
		BigInteger bigInteger = new BigInteger(hex,16);
		
		BigDecimal integer  = new BigDecimal(new BigInteger("009f6b42",16));			
		System.out.println(integer);
		
	}
}






