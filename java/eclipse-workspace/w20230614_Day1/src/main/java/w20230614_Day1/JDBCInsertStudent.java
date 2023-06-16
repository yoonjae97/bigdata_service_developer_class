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

public class JDBCInsertStudent extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "c##smart01";
		String pw = "oracle01";
		
		String html = "";
		html += "<h1>학생 리스트</h1>";
		html += "<table>";
		/// jdbc 통신 후 학생리스트를 가져옴 -> 학생리스트를 문자열로 변환
		html += "<tr>"
				+ "<th>num</th>"
				+ "<th>name</th>"
				+ "</tr>";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String aStr = req.getParameter("num"); // 쿼리스트링에 포함된 a를 찾아서 반
		String bStr = req.getParameter("name");
		int a=0;
		
		a = Integer.parseInt(aStr);
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "INSERT INTO TEST VALUES (" + a + ", '" +bStr + "')";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			
			sql = "SELECT * FROM TEST";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 질의어 실행함수 -> table 반환 (ResultSet)
			while(rs.next()) {
				int num = rs.getInt("NUM");
				String name = rs.getString("NAME");
				html += "<tr>";
				html += "<td>" + num + "</td>";
				html += "<td>" + name + "</td>";				
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
