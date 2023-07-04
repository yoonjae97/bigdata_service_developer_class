<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/inc/register/login.css" type="text/css" />
	
<div class="loginFormdiv">
	<form action="<%=request.getContextPath()%>/register/loginOk"
		method="post" class="loginForm" name="loginForm" onsubmit="return loginFormCheck()">
		<fieldset>
			<legend>로그인</legend>
			<ul>
				<li><label for="userid">아이디</label> <input type="text"
					name="userid" id="userid" style="width: 100%;" placeholder="아이디"
					required /></li>
				<li><label for="userpwd">비밀번호</label> <input type="password"
					name="userpwd" id="userpwd" style="width: 100%;" placeholder="비밀번호"
					required /></li>
				<li>
				
					<button type="button" onclick="history.back();">이전페이지로</button>
					<button type="submit">로그인</button>
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/register/registerForm'">회원가입</button>
				</li>
			</ul>
		</fieldset>
	</form>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	function loginFormCheck() {
		// 아이디 존재유무ㅜ
		if (document.getElementById("userid").value == "") { // id가 userid인 input태그 값 확인
			alert("아이디를 입력하세요");
			return false;
		}
		if (document.getElementById("userpwd").value == "") { // id가 userpwd인 input태그 값 확인
			alert("비밀번호를 입력하세요");
			return false;
		}
		return true;
	}
</script>