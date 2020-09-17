package top.yancc.java.property;

// property 
public class GetPropertys {

	public static void main(String[] args) {
		
		String encoding = System.getProperty("file.encoding"); // 默认编码字符集
		System.out.println(encoding);
		
		String os_name = System.getProperty("os.name"); // 系统OS
		System.out.println(os_name);

		
		String propertys = System.getProperty("System.getProperty()");
		System.out.println(propertys);
	}

}
