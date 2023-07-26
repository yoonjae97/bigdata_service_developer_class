<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/inc/viewsCss/FindIdStyle.css"
	type="text/css" />
	<style>
	.div-wrapper .element-3{
		cursor:pointer;
	}
	</style>
</head>
<body>
	<div class="div-wrapper">
		<div class="overlap-group">
			<div class="login-button">
				<button class="element">확인</button>
			</div>
			<input type="password" class="password-fill"
				placeholder="비밀번호를 입력해주세요" /> <input type="text"
				class="username-fill" placeholder="아이디를 입력해주세요" />
			<div class="text-wrapper">이메일</div>
			<div class="element-2">이름</div>
			<div class="element-3" onclick="location='pwSearch'">비밀번호 찾기</div>
			<div class="element-4">회원가입시 등록한 정보를 입력해주세요</div>
			<div class="element-5">비밀번호가 기억나지 않는다면?</div>
		</div>
		<div class="element-6">아이디 찾기</div>
	</div>

</body>
