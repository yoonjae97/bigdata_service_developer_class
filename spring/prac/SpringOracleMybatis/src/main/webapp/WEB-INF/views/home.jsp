<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div>
		<a href="/home/test?num=100&name=홍길동">접속하기</a> 
		<a href="/home/test2?no=200&id=ggm">접속하기2</a>
		<a href="/home/test3?num=300&username=이순신&tel=010-1234-5678">접속하기3</a>
		<!-- "test5?page=4와 아래 href가 같다. -->
		<p><%=request.getContextPath() %></p>
		<a href="${pageContext.request.contextPath}/test5/5">접속하기4</a>
	</div>
	<div>
		<h2>받은 데이터</h2>
		<ol>
			<%
			String name = request.getParameter("name");
			String no = request.getParameter("no");
			String num = request.getParameter("num");
			if (name != null) {
			%>
			<li>이름 : ${name }</li>
			<li>연락처 : ${tel }</li>

			<%
			} else if (no != null) {
			%>
			<li>이름 : ${name }</li>
			<li>연락처 : ${tel }</li>
			<li>${no },${id }</li>

			<%
			} else if (num != null) {
			%>
			<li>번호 : ${dto.num }</li>
			<li>이름 : ${dto.username }</li>
			<li>연락처 : ${dto.tel }</li>
			<%
			}
			%>
		</ol>
	</div>
	<div>
		<h2>post방식의 전송</h2>
		<form method="post", action="<%=request.getContextPath() %>/test4">
			이름 : <input type="text" name="username" /><br /> 
			주소 : <input type="text" name="addr" /><br /> 
			<input type="submit" value="서버로 보내기" />
		</form>
	</div>
	<img src="<%=request.getContextPath()%>/img/girl_laughing.jpg" />


