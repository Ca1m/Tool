package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test_mysql {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://10.20.86.101:3306/ca_63_rsa_ocsp";
	static final String DB_URL = "jdbc:mysql://10.20.86.101:3306/ca_55_jy";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, "root", "11111111");
		
			conn.setAutoCommit(false);//JDBC中默认是true，自动提交事务
			
			// 执行查询
			System.out.println(" 实例化Statement对象...");
			stmt = conn.createStatement();
			String sql;
			
			//sql = "SELECT ID, AUTHCODE FROM CERTINFO WHERE ID=48867";
			sql = "SELECT * FROM CA_CERTINFO";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				String certSN = rs.getString("CERTSN");
				
				System.out.println("certSN: " + certSN);
			}
			/*
			sql = "UPDATE CERTINFO SET AUTHCODE='authcode_3' WHERE ID='48867' LIMIT 1";
			stmt.executeUpdate(sql);
			sql = "SELECT ID, AUTHCODE FROM CERTINFO WHERE ID=48867";
			
			ResultSet rs_1 = stmt.executeQuery(sql);
			while (rs_1.next()) {
				int id = rs_1.getInt("id");
				String authcode = rs_1.getString("AUTHCODE");
				System.out.println("ID: " + id);
				System.out.println("authcode: " + authcode);
			}
			*/
			conn.commit();
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
}