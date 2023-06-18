package prac1;

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

public class memberList extends HttpServlet {
	private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String user = "c##smart01";
	private final String pw = "oracle01";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String html = "";
		
		html += "<h1>회원 리스트</h1>";
		html += "<h2><a href='memberRegister.html'>회원 등록</a></h2>";
		html += "<table>";
		
		html += "<tr>"
				+ "<th>mem_seq</th>"
				+ "<th>mem_id</th>"
				+ "<th>mem_pw</th>"
				+ "<th>회원 상세</th>";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "SELECT * FROM MEMBER";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("MEM_SEQ");
				String id = rs.getString("MEM_ID");
				String pw = rs.getString("MEM_PASSWORD");
				
				html += "<tr>";
				html += "<td>" + num + "</td>";
				html += "<td>" + id + "</td>";
				html += "<td>" + pw + "</td>";
				html += "<td><a href='./memberDetail.do?num="
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
