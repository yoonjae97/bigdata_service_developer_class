package mvc20230619.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnection {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String user = "c##smart01";
	private static final String pw = "oracle01";
	private static ArrayList<Connection> connList;
	private static Connection conn;
	
	// getConn or getInstance : 싱글톤 패턴 이름 짓는법
	// throws Exception : getConn()에서 예외처리를 꼭 하라
	public static Connection getConn() throws Exception{
		if (conn==null || conn.isClosed()) {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
		}
		return conn;
	}
	//** 싱글톤 패턴 + Connection pool을 많이 한다.
	// db 접속을 1개의 객체가 다 처리하면 다른 유저의 요청이 완료될 때 까지 쿼리실행을 기다린다
	// (db의 접속 1개가 동시에 sql실행을 못한다.
	// db 접속 10개 정도 미리 만들어 놓고 그것을 돌려가면서 사용 -> connection pool
}
