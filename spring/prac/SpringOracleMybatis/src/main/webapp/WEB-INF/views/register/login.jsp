<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function logChk() {
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
<main>
	<h1>로그인 폼</h1>
	<form method="post"
		action="<%=request.getContextPath()%>/register/loginOk"
		onsubmit="return logChk()">
		<!-- logChk return값에 return을 해서 onsubmit에 값 전달 -->
		<ul>
			<li><input type="text" name="userid" id="userid"
				placeholder="아이디 입력" /></li>
			<li><input type="password" name="userpwd" id="userpwd"
				placeholder="비밀번호 입력" /></li>
			<li><input type="submit" value="로그인" /></li>
			<li>
			<!-- 이름과 연락처를 입력받아 DB에서 아이디와 이메일을 조회한 후 이메일로 아이디를 보낸다. -->
				<div>
					<a href="<%=request.getContextPath()%>/register/idSearch">아이디
						찾기</a>이메일로 보내기
				</div>
				<div>
					<a href="#">비밀번호 찾기</a>
				</div>
				<div>
					<a href="<%=request.getContextPath()%>/register/regForm">회원가입</a>
				</div>
			</li>
		</ul>
	</form>
</main>
