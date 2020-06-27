package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Infosec_jy
 *
 * 同样的数据量，加一个左连接 ，性能从 1ms ---》 16ms
 * 同样的数据量，用 Number做时间段判断 和 用 varchar 做时间段判断，  400ms---》5ms
 * 
 * 13万数据量不大的数据，16ms
 * 7 万  数据量大的数据，5ms
 *
 */
public class Test_db_v {

	public static final String URL = "jdbc:oracle:thin:@10.20.84.30:1521:orcl";
	public static final String USER = "test_jy";
	public static final String PASSWORD = "11111111";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		// 1.加载驱动程序
		Class.forName("oracle.jdbc.OracleDriver");

		select_CA_Varchar_(); // varchar_ 查询                                  16ms
		select_CA_Varchar__();  //  varchar_ 查询   没有左连接     1ms
		
		select_KMC_Number(); // Number_ 查询                                       400ms
		select_KMC_Varchar__(); // Varchar                 5ms

	}
	// CA_ Varchar
	public static void select_CA_Varchar_() throws SQLException {

		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql_ = "select /*+ FIRST_ROWS */ * from\r\n"
				+ "       ( select infosecTmpTble.*, rownum infoseRowNum from\r\n"
				+ "             (select LOGSN, i.USERNAME OPERATOR, OPTYPE, OPTIME, TARGET, RETURNCODE, REQUESTADDR\r\n"
				+ "              from OPERATIONLOG lg left join ADMININFO i on lg.OPERATOR=i.USERNAMEREF where lg.LASTAUDITID is null\r\n"
				+ "                and lg.OPTIME >='20200521035000Z' and lg.OPTIME <='20200528035000Z' order by lg.OPTIME desc ) infosecTmpTble where rownum<=45 )\r\n"
				+ "where infoseRowNum>=31";
		PreparedStatement pre = conn.prepareStatement(sql_);
		Long time_1 = new Date().getTime();
		ResultSet rs = pre.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getInt(1));
		}
		System.out.println("varchar 消耗时间：" + (new Date().getTime() - time_1));

		conn.close();
	}

	// CA_ Varchar
	public static void select_CA_Varchar__() throws SQLException {

		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql_ = "select /*+ FIRST_ROWS */ * from\r\n"
				+ "       ( select infosecTmpTble.*, rownum infoseRowNum from\r\n"
				+ "             (select LOGSN, OPERATOR, OPTYPE, OPTIME, TARGET, RETURNCODE, REQUESTADDR\r\n"
				+ "              from OPERATIONLOG lg  where lg.LASTAUDITID is null\r\n"
				+ "                and lg.OPTIME >='20200521035000Z' and lg.OPTIME <='20200528035000Z' order by lg.OPTIME desc ) infosecTmpTble where rownum<=45 )\r\n"
				+ "where infoseRowNum>=31";
		PreparedStatement pre = conn.prepareStatement(sql_);
		Long time_1 = new Date().getTime();
		ResultSet rs = pre.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getInt(1));
		}
		System.out.println("varchar 消耗时间：" + (new Date().getTime() - time_1));
		conn.close();
	}

	// KMC_ Number
	public static void select_KMC_Number() throws SQLException {

		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

		String sql_ = "select /*+ FIRST_ROWS */ * from ( select infosecTmpTble.*, rownum infoseRowNum from (\r\n"
				+ "      select LOGID,OPTYPE,TARGET,OPRESULT,OPERATOR,REQTIME,REQIP,REQ,RESP,AUDITID from OPERTATION_LOG where REQTIME >=1590396341113 and REQTIME <= 1590396342664\r\n"
				+ "	       ORDER BY LOGID DESC ) infosecTmpTble where rownum<=45) where infoseRowNum>=31";
		PreparedStatement pre = conn.prepareStatement(sql_);
		Long time_1 = new Date().getTime();
		ResultSet rs = pre.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getInt(1));
		}
		System.out.println("KMC_Number 消耗时间：" + (new Date().getTime() - time_1));

		conn.close();
	}

	// KMC_ Varchar
	public static void select_KMC_Varchar__() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql_ = "select /*+ FIRST_ROWS */ * from ( select infosecTmpTble.*, rownum infoseRowNum from (\r\n"
				+ "      select LOGID,OPTYPE,TARGET,OPRESULT,OPERATOR,REQTIME,REQIP,REQ,RESP,AUDITID from OPERTATION_LOG where VARCHAR_ >='20200521035000Z' and VARCHAR_ <= '20200528035000Z'\r\n"
				+ "	       ORDER BY LOGID DESC ) infosecTmpTble where rownum<=45) where infoseRowNum>=31";
		PreparedStatement pre = conn.prepareStatement(sql_);
		Long time_1 = new Date().getTime();
		ResultSet rs = pre.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getInt(1));
		}
		System.out.println("KMC_Varchar 消耗时间：" + (new Date().getTime() - time_1));
		conn.close();
	}

}
