
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<h1>로그인 폼</h1>
	<span id="idDisplay"></span>
	<form method="post"
		action="<%=request.getContextPath()%>/register/regIdFind">
		<!-- logChk return값에 return을 해서 onsubmit에 값 전달 -->
		<ul>
			<li><input type="text" name="namefind" id="namefind"
				placeholder="이름 입력" /></li>
			<li><input type="text" name="emailfind" id="emailfind"
				placeholder="이메일 입력" /></li>
			<li><input type="submit" value="아이디찾기" /></li>
		</ul>
	</form>
</main>
<script>
function displayId(id) {
    var idDisplay = document.getElementById("idDisplay");
    idDisplay.textContent = id ? "아이디: " + id : "가입한 아이디가 없습니다.";
}
</script>