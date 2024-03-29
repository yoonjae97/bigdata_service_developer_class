package w20230614_Day1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// /student/list.do path의 호출이 오면 톰캣이 L02JDBCStudentList 실행 후 응답 (동적페이지)
// 학생자료 조회(DQL select는 get)
// 학생자료 등록 수정 삭제(DML post)
// @ 어노테이션 : 컴파일러가 검사하거나 (Override, functionalInterface) 자동완성 (Webservlet)
// WebServlet : DD(web.xml, 웹앱 설정) 배포서술자에 어떤 요청이 왔을 때 어떤 동적 리소스를 호출할건지 정의하는 것
// 배포서술자 : 톰캣 서버에 개발자가 만든 웹앱을 배포할 때 이렇게 동작할 거라는 설정을 정의하는 곳

public class L02JDBCStudentList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "c##smart01";
		String pw = "oracle01";
		
		String html = "";
		html += "<h1>학생 리스트</h1>";
		html += "<h2><a href='L05StudentRegister.html'>학생 등록</a></h2>";
		html += "<table>";
		/// jdbc 통신 후 학생리스트를 가져옴 -> 학생리스트를 문자열로 변환
		html += "<tr>"
				+ "<th>num</th>"
				+ "<th>name</th>"
				+ "<th>상세</th>"
				+ "</tr>";
		
		// java.sql.* : jdbc 패키지 자바로 db 접속을 도와주는 라이브러리(모듈)
		Connection conn = null;
		PreparedStatement pstmt = null; //sqlinjection 해킹 예방
		ResultSet rs = null;
		// 통신, 접속은 오류를 동반하기 때문에 예외처리
		
		try {
			Class.forName(driver);
			// DriverManager 오라클에 접속할 때 오라클 드라이버를 동적 로딩으로 객체 생성한다.
			// 동적로딩 : 객체가 필요할 때 알아서 만들어 사용한다.
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "SELECT * FROM STUDENT";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 질의어 실행함수 -> table 반환 (ResultSet)
			while(rs.next()) {
				int num = rs.getInt("NUM");
				String name = rs.getString("NAME");

				String birth = rs.getString("BIRTHDAY");
				html += "<tr>";
				html += "<td>" + num + "</td>";
				html += "<td>" + name + "</td>";
				html += "<td>" + birth + "</td>";
				html += "<td><a href='./detail.do?num="
						+ num 
						+ "'>"
						+ "상세"
						+ "</a></td>";
				html += "</tr>";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		html += "</table>";
		resp.setContentType("text/html;charset=UTF-8;");
		resp.getWriter().append(html);	
	}
}
/* 초기형태의 웹앱 Model1
 * 특징 : 하나의 동적 리소스의 db접속(Model), 요청응답처리(Controller, 응답할 html (View)가 같이 존재한다.
 * Model : DBA가 작업하는 곳 (db 튜닝, 쿼리 생성, 스키마 관리...)
 * Controller : Backend 개발자가 작업하는 곳 (응용 앱을 만드는 사람)
 * View : Frontend 개발자 or 퍼블리셔 or 디자이너가 작업하는 곳
 * 하나의 동적 리소스를 여러 개발자가 공유하기 때문에 수정 통합하기 힘들다.
 * Sass Less css를 재사용하기 위해 객체지향 문법을 사용하도록 돕는다.
 * TypeScript, Sass Less 같은 프론트엔드 기술 사용 불가능(css를 객체지향 문법을 사용하도록 돕는다.)
 * Model 기술 중에 Mybatis 나 JPA 같은 쿼리와 접속을 관리하는 라이브러리를 사용할 수 없다.
 * 보안에 취약하다
 * 
 * Model2 MVC 패턴 : MVC를 분리해서 기술을 적용하는 디자인 패턴
 * 디자인 패턴 : 코드를 재사용하기 위해 구조를 약속 통일하는 것 (구조를 익히기 어렵다.)
 * 
 * 
 * 
 */

