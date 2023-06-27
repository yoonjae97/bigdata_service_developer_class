<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<main>
		<h1>회원가입 폼</h1>
		<form action="<%=request.getContextPath() %>/register/registerOk" method="post">
			<ul>
				<li>아이디 : <input type="text" name="userid" id="userid"/></li>
				<li>비밀번호 : <input type="password" name="userpwd" id="userpwd"/></li>
				<li>비밀번호확인 : <input type="password" name="userpwd2" id="userpwd2"/></li>
				<li>이름 : <input type="text" name="username" id="username"/></li>
				<li>연락처 : <input type="text" name="tel1" id="tel1" size="4"/> -
						   <input type="text" name="tel2" id="tel2" size="4"/> -
						   <input type="text" name="tel3" id="tel3" size="4"/></li>
				<li>이메일 : <input type="email" name="email" id="email"/></li>
				<li><button>회원가입하기</button></li>
			</ul>
		</form>
	</main>
