package j20230613_Day10;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class createTable {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String sql = "create table student (num number primary key, name varchar2(20)"
					+ ", phone varchar2(13), address varchar2(50), birthday varchar2(8))";
			stmt.executeQuery(sql);
			System.out.println("테이블 생성이 완료되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 = " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, stmt);
		}
		
		
		
		
	}

}
