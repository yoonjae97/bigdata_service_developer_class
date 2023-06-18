package prac1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class memberRegister extends HttpServlet {
	
	private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String user = "c##smart01";
	private final String pw = "oracle01";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String id = req.getParameter("id");
		String pw1 = req.getParameter("pw");
		String ageStr = req.getParameter("age");
		String name = req.getParameter("name");
		String birthday = req.getParameter("birthday");
		String sex = req.getParameter("sex");
		String sql = null;
		int register = 0;
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			sql = "INSERT INTO MEMBER (MEM_SEQ, MEM_ID, MEM_PASSWORD, "
					+ "MEM_AGE, MEM_NAME, MEM_BIRTH, MEM_SEX) VALUES (MEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			Class.forName(driver); // 동적로딩할 클래스를 미리 찾아두는 것
			conn = DriverManager.getConnection(url, user, pw); 
			// jdbc:oracle; -> 오라클 드라이버를 찾아서 생성(동적로딩)
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw1);
			pstmt.setInt(3,  Integer.parseInt(ageStr));
			pstmt.setString(4, name);
			pstmt.setString(5, birthday);
			pstmt.setString(6, sex);
			
			register = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// num이 수가 아니거나 없을 때
			// num가 중복되었을 때
			// 접속을 못하거나 db에 문제가 있을 때
//			resp.sendError(500, "num는 꼭 입력되어야 하며 중복될 수 없습니다.(서버오류일 수 있습니다.");
//			return;
		} finally {
			System.out.println(id + pw1 + ageStr+name+birthday+sex);
			System.out.println(sql);
			
			try {
				if(conn!=null) conn.close();
				if(conn!=null) pstmt.close();
//				if(conn!=null) rs.close();
			} catch( SQLException e) {
				e.printStackTrace();
			}
		}
		if (register>0) {
			resp.sendRedirect("./memberList.do");
		} else { // 등록은 실패 오류가 발생하는 경우가 대부분. 0을 잘 반환하지 않는다.
			resp.sendRedirect("./memberRegister.html");
	}
	}
}
