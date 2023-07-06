<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/inc/register/login.css"
	type="text/css" />

<div class="loginFormdiv">
			<h2>아이디찾기</h2>
			<ul>
				<c:if test="${userId!=null}">
					<li>가입하신 아이디는 ${userId} 입니다</li>
					<button
						onclick='location.href="${pageContext.request.contextPath}/register/login"'>로그인으로</button>
					<button
						onclick='location.href="${pageContext.request.contextPath}/register/findPwdForm"'>비밀번호찾기</button>
				</c:if>
				<c:if test="${userId==null }">
					<li>가입하신 아이디가 존재하지 않습니다</li>
					<button
						onclick='location.href="${pageContext.request.contextPath}/register/login"'>로그인으로</button>
					<button
						onclick='location.href="${pageContext.request.contextPath}/register/registerForm"'>회원가입으로</button>
				</c:if>
			</ul>

</div>