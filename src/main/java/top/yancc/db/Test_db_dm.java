package top.yancc.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.Query;

import cn.com.infosec.netcert.framework.dao.jdbc.Parameter;
import cn.com.infosec.netcert.framework.dao.jdbc.Parameters;
import cn.com.infosec.netcert.framework.dao.jdbc.QueryResult;



public class Test_db_dm {

	public static void main(String[] args) throws Exception {
	
		query();
		
	}

	
	
	public static void query() throws Exception {

		String jdbcString = "dm.jdbc.driver.DmDriver";
		String urlString = "jdbc:dm://10.20.84.100:5237";

		String userName = "TESET_JY_1";
		userName = "CA6330SRR";
		String passWord = "111111111";

		Class.forName(jdbcString);

		// 连接
		Connection connection = DriverManager.getConnection(urlString, userName, passWord);
		
		String sql;
		sql = "select MAX(CDPID) m from CERTINFO";
		Parameters ps = new Parameters();
		PreparedStatement st = connection.prepareStatement(sql);
		
		
		//sql = "SELECT ID, AUTHCODE FROM CERTINFO WHERE ID=48867";
		
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			//String m = rs.getString("m");
			BigDecimal d = rs.getBigDecimal("m");
			int cdp = d.intValue();
			System.out.println("CDPID: " + cdp);
		}
		try {
			st.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		connection.close();
	}
	
	public static void operation(Connection connection) throws Exception {

		/*
		 * 1. 构造插入数据库语句； 2. 创建语句对象； 3. 为参数赋值； 4. 执行语句； 5. 关闭语句。
		 */
		System.out.println("---------------- 增 加 开 始 ----------------\n");
		String insertStr = "INSERT INTO XXX.XXX.XXX(XXX,XXX,XXX) " + "VALUES(?,?,?)";
		insertStr = "insert into USERCERTENTITY (CERTSN,CERTENTITY,SUBJECTKEYID) values ('11111111','MIICIjCCAcWgAwIBAgIFOds3CDcwDAYIKoEcz1UBg3UFADAxMQswCQYDVQQGEwJjbjEQMA4GA1UECgwHaW5mb3NlYzEQMA4GA1UECwwHaW5mb3NlYzAeFw0yMDA3MjAwMzE0NTBaFw0yMDA4MTkwMzE0NTBaMDYxCzAJBgNVBAYTAmNuMScwJQYDVQQDDB5qemowMDFfMTU5NTIxNTYyODE0N18yODA5MDA0ODAwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQpDYhc4/FIItmg9bErhDjRmQy2SvWRyJeMqrhH4PJQOAQGNaRfDNVUPJN7ytN3ZONqi4U6Mc5Jc1HcYMwUCGpco4HCMIG/MB8GA1UdIwQYMBaAFAh0+wyXH4lNok6zNH6hiNKUMPxzMAkGA1UdEwQCMAAwYgYDVR0fBFswWTBXoFWgU6RRME8xDjAMBgNVBAMMBWNybDUwMQwwCgYDVQQLDANjcmwxEDAOBgNVBAsMB2luZm9zZWMxEDAOBgNVBAoMB2luZm9zZWMxCzAJBgNVBAYTAmNuMA4GA1UdDwEB/wQEAwIGwDAdBgNVHQ4EFgQUc7SwQFAD49tI1mUAx2e8LgiaCwUwDAYIKoEcz1UBg3UFAANJADBGAiEA1UW1wAgPpFiM+73MHepA84EFGJBKBtI+xuT0CHi4BZ8CIQCFXqckGp0toIAF7zX/fjioPm8hIRaF9/cNoRcTL06g/g==','c7SwQFAD49tI1mUAx2e8LgiaCwU=')";
		PreparedStatement pstmt = connection.prepareStatement(insertStr);
		
		int result = pstmt.executeUpdate();
		System.out.println(result);
		
		/*
		 * 1. 构建更新语句； 2. 创建语句对象； 3. 为参数赋值； 4. 执行语句； 5. 关闭语句。
		 */

		System.out.println("---------------- 修 改 开 始 ----------------\n");

		String updateStr = "UPDATE XXX.XXX.XXX SET name = ? where id = 666666666";
		PreparedStatement pstmtUpdate = connection.prepareStatement(updateStr);
		pstmtUpdate.setString(1, "呵呵呵呵呵呵");
		pstmtUpdate.executeUpdate();
		pstmtUpdate.close();
		printfResult(connection, "select * from EMS.EMS.ACLINE");

		System.out.println("---------------- 修 改 结 束 ----------------\n");

		// 查询
		/*
		 * 1. 创建语句对象； 2. 执行查询； 3. 显示结果集； 4. 关闭结果集； 5. 关闭语句对象。
		 */
		System.out.println("---------------- 查 询 开 始 ----------------");
		String queryStr = "select * from XXX.XXX.XXX";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(queryStr);
		displayResultSet(rs);

		rs.close();
		stmt.close();

		System.out.println("---------------- 查 询 结 束 ----------------\n");

	}

	private static void displayResultSet(ResultSet rs) throws SQLException {

		// 取得结果集元数据
		ResultSetMetaData rsmd = rs.getMetaData();
		// 取得结果集所包含的列数
		int numCols = rsmd.getColumnCount();
		// 列头
		for (int i = 1; i <= numCols; i++) {
			if (i > 1) {
				System.out.print(",");
			}
			System.out.print(rsmd.getColumnLabel(i));
		}
		System.out.println("");

		// 所有数据
		while (rs.next()) {
			for (int i = 1; i <= numCols; i++) {
				if (i > 1) {
					System.out.print(",");
				}
				// 普通字段
				System.out.print(rs.getString(i));
			}
			System.out.println("");
		}
	}

	public static void test() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		C3P0 c3p0 = C3P0.getInstance();
	
		// 任务
		for (int i = 0; i < 1000; i++) {
			final int index = i;
			
			// 从线程池获取一个 线程，执行逻辑代码
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println("index:" + index);
						Connection connection = c3p0.getConnection();		
						String insertStr = "insert into USERCERTENTITY (CERTSN,CERTENTITY,SUBJECTKEYID) values (?,'MIICIjCCAcWgAwIBAgIFOds3CDcwDAYIKoEcz1UBg3UFADAxMQswCQYDVQQGEwJjbjEQMA4GA1UECgwHaW5mb3NlYzEQMA4GA1UECwwHaW5mb3NlYzAeFw0yMDA3MjAwMzE0NTBaFw0yMDA4MTkwMzE0NTBaMDYxCzAJBgNVBAYTAmNuMScwJQYDVQQDDB5qemowMDFfMTU5NTIxNTYyODE0N18yODA5MDA0ODAwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQpDYhc4/FIItmg9bErhDjRmQy2SvWRyJeMqrhH4PJQOAQGNaRfDNVUPJN7ytN3ZONqi4U6Mc5Jc1HcYMwUCGpco4HCMIG/MB8GA1UdIwQYMBaAFAh0+wyXH4lNok6zNH6hiNKUMPxzMAkGA1UdEwQCMAAwYgYDVR0fBFswWTBXoFWgU6RRME8xDjAMBgNVBAMMBWNybDUwMQwwCgYDVQQLDANjcmwxEDAOBgNVBAsMB2luZm9zZWMxEDAOBgNVBAoMB2luZm9zZWMxCzAJBgNVBAYTAmNuMA4GA1UdDwEB/wQEAwIGwDAdBgNVHQ4EFgQUc7SwQFAD49tI1mUAx2e8LgiaCwUwDAYIKoEcz1UBg3UFAANJADBGAiEA1UW1wAgPpFiM+73MHepA84EFGJBKBtI+xuT0CHi4BZ8CIQCFXqckGp0toIAF7zX/fjioPm8hIRaF9/cNoRcTL06g/g==','c7SwQFAD49tI1mUAx2e8LgiaCwU=')";
						PreparedStatement pstmt = connection.prepareStatement(insertStr);

						BigDecimal certSN = new BigDecimal(String.valueOf(index)).add(new BigDecimal("248490952718")).subtract(new BigDecimal(1));
							
						System.out.println("--------------------------thread:" + Thread.currentThread().getName());
						
						pstmt.setBigDecimal(1, certSN);	
						int result = pstmt.executeUpdate();
						System.out.println("执行的结果是：" + result);

						pstmt.close();
						connection.close(); // 关闭连接
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println("---------------- 增 加 结 束 ----------------\n");
	}
	
	
	static void printfResult(Connection connection, String sqlStr) throws SQLException {

		String queryStr = sqlStr;
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(queryStr);
		displayResultSet(rs);
		stmt.close();
	}

}
