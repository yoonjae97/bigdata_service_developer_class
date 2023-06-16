package w20230614_Day1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student/register.do")
public class L05JDCBCStudentRegister extends HttpServlet {
	int test; // 값을 안줘도 0으로 저장되어있다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numStr = req.getParameter("num");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String birthday = req.getParameter("birthday");
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "c##smart01";
		String pw = "oracle01";
		
		int register = 0;
		Connection conn=null;
		// PreparedStatement pstmt // undefined(지역변수에서는 절대 사용할 수 없다.)
		// 전역변수는 undefined를 null이나 0으로 변환
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		try {
			String sql = "INSERT INTO STUDENT (NUM, NAME, PHONE, ADDRESS, BIRTHDAY) VALUES (?, ?, ?, ?, ?)";
			Class.forName(driver); // 동적로딩할 클래스를 미리 찾아두는 것
			conn = DriverManager.getConnection(url, user, pw); 
			// jdbc:oracle; -> 오라클 드라이버를 찾아서 생성(동적로딩)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  Integer.parseInt(numStr));
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setString(5, birthday);
			register = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// num이 수가 아니거나 없을 때
			// num가 중복되었을 때
			// 접속을 못하거나 db에 문제가 있을 때
//			resp.sendError(500, "num는 꼭 입력되어야 하며 중복될 수 없습니다.(서버오류일 수 있습니다.");
//			return;
		} finally {
			try {
				if(conn!=null) conn.close();
				if(conn!=null) pstmt.close();
//				if(conn!=null) rs.close();
			} catch( SQLException e) {
				e.printStackTrace();
			}
		}
		if (register>0) {
			resp.sendRedirect("./list.do");
		} else { // 등록은 실패 오류가 발생하는 경우가 대부분. 0을 잘 반환하지 않는다.
			resp.sendRedirect("./L05StudentRegister.html");
	}
		
	}
}
