<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="w20230619.dto.Studentdto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 상세</title>
</head>
<body>
	<% 
		String numStr = request.getParameter("num");
		int num = 0;
		try {
			num = Integer.parseInt(numStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(400, "num를 꼭 보내야 합니다.");
			return;			
		}
		Studentdto studentdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "c##smart01";
		String pw = "oracle01";
		
		try {
			String sql = "SELECT * FROM STUDENT WHERE num=?";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				studentdto = new Studentdto();
				studentdto.setNum(rs.getInt("num"));
				studentdto.setName(rs.getString("name"));
				studentdto.setPhone(rs.getString("phone"));
				studentdto.setAddress(rs.getString("address"));
				studentdto.setBirthday(rs.getString("birthday"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (studentdto==null) { // 조회된 내역이 없다! 1. 레코드가 존재하지 않습니다. 2. 리스트로 보내기
			response.sendRedirect("./l01_student_list.jsp");
			return;
		}
		
		
	%>
	<!--  학생 리스트를 dto를 사용해서 detail처럼 바꾸기 -->
	<h1>학생 상세 조회</h1>
	<p><strong>num : </strong><span><%=studentdto.getNum() %></span></p>
	<p><strong>name : </strong><span><%=studentdto.getName() %></span></p>
	<p><strong>phone : </strong><span><%=studentdto.getPhone() %></span></p>
	<p><strong>address : </strong><span><%=studentdto.getAddress() %></span></p>
	<p><strong>birthday : </strong><span><%=studentdto.getBirthday() %></span></p>
	
	
	
	
</body>
</html>