package j20230613_Day10;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStudentConnectionFactory {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();

			stmt = conn.createStatement();

			String sql = "select * from student order by num";

			rs = stmt.executeQuery(sql);

			//  System.out.println("학번 \t이름 \t전화번호 \t주소 \t생년월일");
			System.out.printf("%6s %3s %4s %s %s", "num", "name", "phone", "address", "birthday");
			System.out.println();
			System.out.println("===========================================================");
			
			while(rs.next()) {

				int num = rs.getInt("num");
	
				String name = rs.getString("name");
	
				String phone = rs.getString("phone");
	
				String address = rs.getString("address"); 
	
				String birthday = rs.getString("birthday").substring(0, 8 );
				System.out.printf("%6d %3s %4s %s %s", num, name, phone, address, birthday);
				System.out.println();
				System.out.println("===========================================================");
	
				}

			} catch(SQLException e) {

			System.out.println("SQL 오류 = " + e.getMessage());

			} finally {

			ConnectionFactory.close(conn, stmt, rs);

			}
		
	}

}
