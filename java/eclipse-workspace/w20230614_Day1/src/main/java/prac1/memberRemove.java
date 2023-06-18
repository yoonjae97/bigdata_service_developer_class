package prac1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class memberRemove extends HttpServlet {
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
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		int remove = 0;

		try {
			String sql = "DELETE FROM MEMBER WHERE MEM_SEQ=?";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			remove = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500, "예기치 못한 서버 오류 발생");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			if (remove > 0) {
				resp.sendRedirect("./memberList.do");
			} else {
				resp.sendRedirect("./memberModify.do?num=" + numStr);
			}
		}
	}
}