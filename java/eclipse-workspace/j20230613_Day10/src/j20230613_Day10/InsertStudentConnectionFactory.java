package j20230613_Day10;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertStudentConnectionFactory {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;	
		

		try {

		conn = ConnectionFactory.getConnection();
		stmt = conn.createStatement();

		int num; 
		String name, phone, addr, birth; 
		Scanner sc = new Scanner(System.in);
		String sql;

		
		for (int i=0; ;i++) {
			System.out.println("학생정보를 입력하세요(종료 : -1)");

			System.out.print("번호 : ");
			num = sc.nextInt();
			
			if (num == -1) {
				System.out.println("종료되었습니다.");
				break;
			}
			
			System.out.print("이름 : ");
			name = sc.next();
			
			System.out.print("전화번호 : ");
			phone = sc.next();
			
			sc.nextLine();
			
			System.out.print("주소 : ");
			addr = sc.nextLine(); // 주소에 스페이스가 들어갈 수 있기 때문
			
			System.out.print("생년월일(90/01/01) : ");
			birth = sc.next();
			sql = "insert into student values(" + num + ", '" + name + "'"
					 + ", '" + phone + "', '" + addr + "', '" + birth + "')";
			stmt.executeUpdate(sql);	
			
			System.out.println("학생정보를 입력하였습니다.");
			}
			
		
//		 String sql1 = "insert into student values(1000, '정길동'"
//		 + ", '010-1234-5678', '서울시 서초구', '90/11/12')";
//		 String sql2 = "insert into student values(2000, '임꺽정'"
//		 + ", '010-2345-6789', '서울시 강남구', '91/12/23')";
//		 String sql3 = "insert into student values(3000, '전우치'"
//		 + ", '010-2345-6789', '서울시 양천구', '92/01/04')";
//		 String sql4 = "insert into student values(4000, '일지매'"
//		 + ", '010-3456-4567', '서울시 금천구', '93/02/15')"; 
//		 
//		 String sql5 = "insert into student values(5000, '장길산'"
//				+ ", '010-4567-5678', '서울시 성북구', '94/03/26')";
//		String [] arr = {sql1, sql2, sql3, sql4, sql5};
		
//		for (String i : arr) {
//			int rows = stmt.executeUpdate(i);}
		
		//System.out.println(rows + "명의 학생정보를 저장하였습니다.");
//		int rows = stmt.executeUpdate(sql);
		

		} catch (SQLException e) {

		System.out.println("SQL 오류 = " + e.getMessage());

		} finally {

		ConnectionFactory.close(conn, stmt);

		}


	}

}
