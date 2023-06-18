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

public class memberDetail extends HttpServlet{
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
		} catch (NumberFormatException e) {
			resp.sendError(404);
			return;
		}
		
		String sql = "SELECT * FROM MEMBER WHERE MEM_SEQ=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		PrintWriter out = resp.getWriter();
		String html = "";
		html += "<h1>회원 상세 정보</h1>";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				html += "<p>num : " + rs.getInt("MEM_SEQ") + "</p>";
				html += "<p>id : " + rs.getString("MEM_ID") + "</p>";
				html += "<p>password : " + rs.getString("MEM_PASSWORD") + "</p>";
				html += "<p>age : " + rs.getInt("MEM_AGE") + "</p>";
				html += "<p>name : " + rs.getString("MEM_NAME") + "</p>";
				html += "<p>birthday : " + rs.getString("MEM_BIRTH") + "</p>";
				html += "<p>sex : " + rs.getString("MEM_SEX") + "</p>";
				html += "<a href='./memberModify.do?num=" + rs.getInt("MEM_SEQ") + "'>수정(상세와 같은데 수정 양식이 존재)</a>";
				html += "<a href='./memberList.do'>뒤로가기</a>";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.setContentType("text/html;charset=UTF-8");
		out.print(html);
		
	}
}
