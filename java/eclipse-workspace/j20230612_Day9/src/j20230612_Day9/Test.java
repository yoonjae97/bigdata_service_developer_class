package j20230612_Day9;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//class insert {
//	String name;
//	int grade;
//	String query;
//	
//	String insertQuery (Scanner sc) {
//		System.out.println("추가할 학생 이름 입력해주세요");
//		name = sc.next();
//		System.out.println("추가할 학생의 점수 입력해주세요");
//		grade = sc.nextInt();
//		query = "INSERT INTO JDBCTEST VALUES('" + name + "'," + grade + ")";
//		return query;
//	}
//}
//
//class update {
//	String name;
//	int grade;
//	String query;
//	
//	String updateQuery (Scanner sc) {
//		System.out.println("갱신할 학생 이름 입력해주세요");
//		name = sc.next();
//		System.out.println("갱신할 학생의 점수 입력해주세요");
//		grade = sc.nextInt();
//		
//		query = "UPDATE JDBCTEST SET studentGrade=" + grade + " WHERE studentName='" + name + "'"; 
//		return query;
//	}
//}
//
//class delete {
//	String name;
//	String query;
//	
//	String deleteQuery (Scanner sc) {
//		System.out.println("삭제할 학생 이름 입력해주세요");
//		name = sc.next();
//		
//		query = "DELETE FROM JDBCTEST WHERE studentName='" + name + "'";
//		return query;
//	}
//}
//
//class select {
//	String name;
//	String query;
//	
//	String selectQuery (Scanner sc) {
//		System.out.println("조회할 학생 이름 입력해주세요(모두 조회일 경우 all 입력)");
//		name = sc.next();
//		
//		query = (name.equals("all"))?"SELECT * FROM jdbctest":"SELECT * FROM jdbctest WHERE studentName='" + name + "'";
//
//		return query;
//	}
//}


public class Test {
	String sname;
	int sgrade;
	String name;
	int grade;
	String query;
	
	String insertQuery (Scanner sc) {
		System.out.println("추가할 학생 이름 입력해주세요");
		name = sc.next();
		System.out.println("추가할 학생의 점수 입력해주세요");
		grade = sc.nextInt();
		query = "INSERT INTO JDBCTEST VALUES('" + name + "'," + grade + ")";
		return query;
	}
	
	String updateQuery (Scanner sc) {
		System.out.println("갱신할 학생 이름 입력해주세요");
		name = sc.next();
		System.out.println("갱신할 학생의 점수 입력해주세요");
		grade = sc.nextInt();
		
		query = "UPDATE JDBCTEST SET studentGrade=" + grade + " WHERE studentName='" + name + "'"; 
		return query;
	}
	
	String delete(Scanner sc) {
		System.out.println("삭제할 학생 이름 입력해주세요");
		name = sc.next();
		
		query = "DELETE FROM JDBCTEST WHERE studentName='" + name + "'";
		return query;
	}
	
	String selectQuery (Scanner sc) {
		System.out.println("조회할 학생 이름 입력해주세요(모두 조회일 경우 all 입력)");
		name = sc.next();
		
		query = (name.equals("all"))?"SELECT * FROM jdbctest":"SELECT * FROM jdbctest WHERE studentName='" + name + "'";

		return query;
	}
    public static void main(String[] args) {

    	while(true ) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("실행할 명령을 입력해주세요(select, insert, update, delete)");
    	System.out.println("exit를 입력할 경우 종료합니다");
    	String cm = sc.next();
    	// select는 executeQuery()
    	// insert update delete executeUpdate()
    	
    	if (cm.equals("exit")) {
    		System.out.println("프로그램이 종료되었습니다.");
    		break;
    	}
    	
    	switch(cm) {
    		case "insert": 
    			query = insertQuery(sc);
    			break;
    			
			case "delete":
    			delete deleteTemp = new delete();
    			query = deleteTemp.deleteQuery(sc);
    			break;
			case "update":
				
    			update updateTemp = new update();
    			query = updateTemp.updateQuery(sc);
    			break;
			case "select":
    			select selectTemp = new select();
    			query = selectTemp.selectQuery(sc);
    			break;
    	}
        DBConnection dbConnection = new DBConnection();  // DBConnection 객체 생성

        try {            
        	Connection conn = dbConnection.getConnection();  // DBConnection에서 Connection 객체 얻기
            Statement stmt = conn.createStatement();  // Statement 객체 생성
            if (cm.equals("select")) {            	
            	ResultSet rs = stmt.executeQuery(query);;
            	 while (rs.next()) {  // 결과 출력
            		  System.out.println("USERID: " + rs.getString("studentName"));
	                  System.out.println("studentgrade: " + rs.getInt("STUDENTGRADE"));
              }
            	 rs.close();
             
            }
            else {

            	stmt.executeUpdate(query);  // 쿼리 실행하고 결과 받기

            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       }
    }
}