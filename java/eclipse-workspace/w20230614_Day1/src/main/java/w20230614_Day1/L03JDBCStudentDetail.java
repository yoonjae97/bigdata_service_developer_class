package w20230614_Day1;
// /student/detail.do

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


//@WebServlet("/student/detail.do")
public class L03JDBCStudentDetail extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numStr =req.getParameter("num");
		// status 400 : 클라이언트에게 num 파라미터가 꼭 필요하다고 알려줘야함(오류);
		int num = 0;
		try {
			num = Integer.parseInt(numStr);
		} catch (NumberFormatException e) {
			resp.sendError(400);
			return;
		}
		String sql = "SELECT * FROM STUDENT WHERE num=?";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "c##smart01";
		String pw = "oracle01";
		Connection conn = null;
		PreparedStatement pstmt = null;;
		ResultSet rs = null;
		
		PrintWriter out = resp.getWriter();
		String html = "";
		html += "<h1>학생 상세 정보</h1>";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  num); // int : 정수 데이터는 sql injection 불가능
			// pstmt.setString(2,  html); num = '1000 or 1= 1;'(sql injection 불가능) num=1000 or 1=1; (가능)
			// 작은 따옴표로 문자열 취급을 통해 방지
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				html += "<p>num : " + rs.getInt("num") + "</p>"; // 숫자는 인덱싱인데 잘 안씀
				html += "<p>name : " + rs.getString(2) + "</p>";
				html += "<p>phone : " + rs.getString(3)+ "</p>";
				html += "<p>address : " + rs.getString(4)+ "</p>";
				html += "<p>birthday : " + rs.getString(5) + "</p>";
				html += "<a href='./modify.do?num=" + rs.getInt("num") + "'>수정(상세와 같은데 수정 양식이 존재)</a>";
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		

		resp.setContentType("text/html;charset=UTF-8");
		out.print(html);
		
		
		
		
	}

}
