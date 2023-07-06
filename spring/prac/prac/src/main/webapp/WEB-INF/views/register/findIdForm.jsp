<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/inc/register/login.css"
	type="text/css" />

<div class="loginFormdiv">
	<form action="<%=request.getContextPath()%>/register/findId"
		method="post" class="loginForm" name="loginForm"
		onsubmit="return findIdFormCheck()">
		<fieldset>
			<legend>아이디찾기</legend>
			<ul>
				<li><label for="username">이름</label> <input type="text"
					name="username" id="username" style="width: 100%;" placeholder="가입 시 사용한 이름을 입력해주세요"
					required /></li>
				<li><label for="useremail">이메일</label> <input type="text"
					name="useremail" id="useremail" style="width: 100%;" placeholder="가입 시 사용한 이메일을 입력해주세요"
					required /></li>
				<li>
					<button type="submit">아이디찾기</button>
					<button onclick='location.href="${pageContext.request.contextPath}/register/login"'>취소</button>

				</li>
	
			</ul>
		</fieldset>
	</form>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	function findIdFormCheck() {
		// 아이디 존재유무
		if (document.getElementById("username").value == "") { // id가 username인 input태그 값 확인
			alert("이름를 입력하세요");
			return false;
		}
		if (document.getElementById("useremail").value == "") { // id가 useremail인 input태그 값 확인
			alert("이메일를 입력하세요");
			return false;
		}
		return true;
	}
</script>