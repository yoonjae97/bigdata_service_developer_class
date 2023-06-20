<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="mvc20230619.dto.StudentDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록 양식</title>
</head>
<script src="<%=request.getContextPath()%>/public/js/studentRegister.js" defer ></script>
<body>
	<h1>학생 등록 양식</h1>
	<form name="registerForm" action="./register.do" method="post">
		<p>
			<!-- 수정하기 싫은것은 readonly hidden 2가지로 가능  -->
			<label> num : <input type="text" name="num" value="" />
			</label>
		</p>
		<p>
			<label> name : <input type="text" name="name" value="" />
			</label>
		</p>
		<p>
			<label> phone : <input type="text" name="phone" value="" />
			</label>
		</p>
		<p>
			<label> address : <input type="text" name="address" value="" />
			</label>
		</p>
		<p>
			<label> birthday : <input type="text" name="birthday"
				value="" />
			</label>
		</p>
		<p>
			<button type="reset">초기화</button>
			<button type="submit">제출</button>
		</p>
	</form>


</body>
</html>