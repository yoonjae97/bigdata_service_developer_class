<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="mvc20230619.dto.StudentDto"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생 상세</h1>
	<%
	Object studentObj = request.getAttribute("student");
	StudentDto student = (StudentDto) studentObj;
	%>
	<p><strong>num : <span><%=student.getNum() %></span></strong></p>
	<p><strong>name : <span><%=student.getName()%></span></strong></p>
	<p><strong>phone : <span><%=student.getPhone()%></span></strong></p>
	<p><strong>address : <span><%=student.getAddress()%></span></strong></p>
	<p><strong>birthday : <span><%=student.getBirthday()%></span></strong></p>
	<p><a href="./modify.do?num=<%=student.getNum()%>">수정</a></p>
</body>
</html>