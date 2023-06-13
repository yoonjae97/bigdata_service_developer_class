package j20230613_Day10;

// 1. oracle DB 설치
// 2. jdbc 모듈 설치
// create user c##smart01 identified by oracle01;
// grant connect, resource, dba to c##smart01


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC 프로그램에 공통적으로 사용되는 코드를 메소드로 구현한 클래스
// => 유지보수를 보다 쉽게 하기 위해 생성한 클래스

	public class ConnectionFactory {

	//JDBC Driver 인스턴스 생성 후 DBMS에 접속하여 얻어온 Connection 인스턴스를 반환하는 메소드
	public static Connection getConnection() {
		String driverName = "oracle.jdbc.driver.OracleDriver"; // ojdbc10.jar
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##smart01"; // connect, source, dba 권한 부여
		String password = "oracle01";
		Connection conn = null;

	try {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
	
	} catch (ClassNotFoundException e) {
		System.out.println("Connection 인스턴스를 생성하지 못했습니다.");
		System.exit(0);
		
	} catch (SQLException e) {
	}
	return conn;
	}

	//SQL 자원을 반납하는 메소드
	public static void close(Connection conn) {

	try {
		if(conn != null) 
			conn.close();

	} catch(SQLException e) {
		e.printStackTrace();	
	}
}


	public static void close(Connection conn, Statement stmt) {


	try {

		if(stmt != null) 
			stmt.close();
		
		if(conn != null) 
			conn.close();

	} catch(SQLException e) {
		e.printStackTrace();
	}

}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {

	try {

		if(rs != null) 
			rs.close();
		
		if(stmt != null) 
			stmt.close();
		if(conn != null) 
			conn.close();

	} catch(SQLException e) {
		e.printStackTrace();

		}	

	}

}
