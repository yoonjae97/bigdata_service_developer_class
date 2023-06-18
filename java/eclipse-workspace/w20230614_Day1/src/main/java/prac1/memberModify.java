package prac1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class memberModify extends HttpServlet {
	private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String user = "c##smart01";
	private final String pw = "oracle01";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numStr = req.getParameter("num");
		
		int num = 0;
		try {
			num = Integer.parseInt(numStr);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(400, "num 파라미터가 잘못되었습니다.");
		}
		
		String sql = "SELECT * FROM MEMBER WHERE MEM_SEQ=?";
		
		String name = null, id = null, password = null, birthday = null, sex = null;
		int age = 0;
		int cnt = 0;
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			Class.forName(driver);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  num);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				++cnt;
				num = rs.getInt("MEM_SEQ");
				id = rs.getString("MEM_ID");
				password = rs.getString("MEM_PASSWORD");
				age = rs.getInt("MEM_AGE");
				name = rs.getString("MEM_NAME");
				birthday = rs.getString("MEM_BIRTH");
				sex = rs.getString("MEM_SEX");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500, "접속 중 에러 발생");
			return;
		}
		
		String html = "<h1>회원 수정 양식</h1>";
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		if (cnt == 0) {
			html += "<h2>해당 레코드는 삭제되었거나 존재하지 않습니다.</h2>";
		} else {
			html += "<form action='./memberModify.do' method='post'>";
			html += "<p><label>num : <input name='num' value='"+num+"' readonly /> </label></p>";
			html += "<p><label>id : <input name='id' value='"+id+"' /> </label></p>";
			html += "<p><label>password : <input name='password' value='"+password+"' /> </label></p>";
			html += "<p><label>age : <input name='age' value='"+age+"' /> </label></p>";
			html += "<p><label>name : <input name='name' value='"+name+"' /> </label></p>";
			html += "<p><label>birthday : <input name='birthday' value='"+birthday+"' /> </label></p>";
			html += "<p><label>sex : <input name='sex' value='"+sex+"' /> </label></p>";
			html += "<p><button style='margin-righ:20px;'>수정 양식 제출</button>"
					+ "<a href='./memberRemove.do?num="+num+"'>삭제</a></p>";
			
			html += "</form>";
			
		}
		
		out.print(html);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String numStr = req.getParameter("num");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String ageStr = req.getParameter("age");
		String name = req.getParameter("name");
		String birthday = req.getParameter("birthday");
		String sex = req.getParameter("sex");
		
		resp.setContentType("text/html; charset=UTF-8");
		int modify = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "UPDATE MEMBER SET MEM_ID=?, MEM_PASSWORD=?, MEM_AGE=?, MEM_NAME=?, MEM_BIRTH=?, MEM_SEX=? WHERE MEM_SEQ=?";
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			int age = Integer.parseInt(ageStr);
			pstmt.setInt(3, age);
			pstmt.setString(4, name);
			pstmt.setString(5, birthday);
			pstmt.setString(6, sex);
			int num = Integer.parseInt(numStr);
			pstmt.setInt(7,  num);
			
			//DQL 실행하면 table ResultSet
			//DML 실행시 반환된느 것은 int
			modify = pstmt.executeUpdate();
			// DQL 실행하면 table

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500, "학생 데이터 수정시 db 오류 발생 다시 시도하세요");
			return; // sendError 무조건 함수 종료
		}
		
		if(modify > 0) { // 수정성공
			//resp.getWriter().append("<h1>수정 성공</h1>");
			resp.sendRedirect("./memberDetail.do?num="+numStr);
			// redirect : 서버에서 다른 리소스르르 요청 302
		}else {// 존재하지 않거나 삭제된 레코드
			//resp.getWriter().append("<h1>수정 실패 : 이미 삭제된 레코드 입니다.</h1>");
			resp.sendRedirect("./memberModify.do?num="+numStr);
		}
	}
}
