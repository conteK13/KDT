package com.kosmo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/* oracle 드라이버를 MyWeb/WEB-INF/lib/ojdbc11.jar
 * C:/app/user/product/21c/dbhomeXE/jdbc/lib/ojdbc11.jar
 * 
 * */


public class DBUtil {
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "c##scott";
	private static String pwd = "tiger";
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading 실패!");
			e.printStackTrace();
		}
	}// static initializer----------
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			con =DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}//-------------------------------
	
	public static void close(Connection con) {
		try {
			if(con!=null) con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}//-------------------------------
	
	
	public static void close(Connection con, Statement st) {
		try {
			if(st!=null) st.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}//-------------------------------
	
	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}//-------------------------------
	
	
}
