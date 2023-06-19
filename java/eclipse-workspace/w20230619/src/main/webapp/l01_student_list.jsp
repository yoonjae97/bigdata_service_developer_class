<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="w20230619.dto.Studentdto"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!// 해당 서블릿페이지의 전역%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 리스트</title>
<style>
table {
	border-collapse: collapse;
}

table td, table th {
	border: 1px solid;
}
</style>
</head>
<!-- out.print("<body">) -->
<body>
	<%
	// doGet(HttpServletRequest request, HttpServletResponse response) 
	// 내부와 동일
	// 실무에서 jsp페이지 사용안한다?
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String user = "c##smart01";
	String pw = "oracle01";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<Studentdto> studentList = new ArrayList<Studentdto>();
	try {
		String sql = "SELECT * FROM STUDENT";
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pw);
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			Studentdto studentdto = new Studentdto();
			studentdto.setNum(rs.getInt("num"));
			studentdto.setName(rs.getString("name"));
			studentdto.setPhone(rs.getString("phone"));
			studentdto.setAddress(rs.getString("address"));
			studentdto.setBirthday(rs.getString("birthday"));
			studentList.add(studentdto);

		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
	<h1>학생 리스트 조회</h1>
	<table style="border: 1px solid black;">
		<tr>
			<th>num</th>
			<th>name</th>
			<th>phone</th>
			<th>address</th>
			<th>birthday</th>
			<th>상세</th>
		</tr>
		<%
		for (Studentdto s : studentList) {
		%>
		<tr>
			<td>
				<%
				out.print(s.getNum());
				%>
			</td>
			<td><%=s.getName()%></td>
			<td><%=s.getPhone()%></td>
			<td><%=s.getAddress()%></td>
			<td><%=s.getBirthday()%></td>
			<td><a href="./l01_student_detail.jsp?num=<%=s.getNum()%>">
					상세 </a></td>
		</tr>
		<%
		}
		%>

	</table>



</body>
</html>