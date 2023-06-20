<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="mvc20230619.dto.StudentDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 수정 양식</title>
</head>
<body>
	<h1>학생 수정 양식</h1>
	<%
	Object studentObj = request.getAttribute("student");
	StudentDto student = (StudentDto) studentObj;
	%>
	<form name="modifyForm" action="./modify.do" method="post">
		<p>
			<!-- 수정하기 싫은것은 readonly hidden 2가지로 가능  -->
			<label> num : <%=student.getNum() %> <input type="hidden"
				name="num" value="<%=student.getNum()%>" />
			</label>
		</p>
		<p>
			<label> name : <input type="text" name="name"
				value="<%=student.getName()%>" />
			</label>
		</p>
		<p>
			<label> phone : <input type="text" name="phone"
				value="<%=student.getPhone()%>" />
			</label>
		</p>
		<p>
			<label> address : <input type="text" name="address"
				value="<%=student.getAddress()%>" />
			</label>
		</p>
		<p>
			<label> birthday : <input type="text" name="birthday"
				value="<%=student.getBirthday()%>" />
			</label>
		</p>
		<p>
			<!-- 브라우저의 주소창(window.location)의 주소(href)를 바꾸는 것은 a태그와 같은 행동 -->
			<button type="reset">초기화</button>
			<button type="submit">제출</button>
			<!-- <button onclick="location.href='./remove.do?num=<%=student.getNum() %>';" type="button">삭제</button>
			 -->
			<button name="removeBtn" type="submit">삭제</button>
		</p>

	</form>
	<script type="text/javascript">
		// script의 위치에 상관없이 form을 호출하는 방법
		// 아래 3가지 제외하고는 form태그 이후에 script를 써야한다	// window.load=function(e) {} // 브라우저가 도큐먼트(html)를 모두 로딩(컴포넌트로 만들어서 출력)
		// document.addEventListner("DOMContentsLoaded", (e)=> {})
		// script를 리소스로 지정할 떄 defer 속성을 준다. (html이 모두 로딩되면 해당 스크립트를 다운로드 하겠다.)
		console.log(modifyForm.removeBtn); // form name 아이디처럼 사용가능
		modifyForm.removeBtn.onclick = function (e) {
			location.href = "./remove.do?num="+modifyForm.num.value;
			
		}
	</script>

</body>
</html>