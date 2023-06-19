<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Java Servlet Page</h1>
	<h2>
		<a href="./l01_student_list.jsp">
			jsp로 구현한 학생 리스트
		</a>
	</h2>
	
	
	
	
	
	<h2>
		정적리소스로 취급(파일)되는 동적리소스 jsp 문서
		<%=10*13 %>
	</h2>
	<p>톰캣에서 인터프리터 엔진으로 실행하는 동적리소스</p>
	<ul>
		<li>서블릿 페이지가 컴파일되서 배포해야하기 때문에 컴파일 할때마다 
		서버를 재시작해야 개발이 가능(생산성이 떨어진다)해서 jsp 만들었다.</li>
		<li>서블릿 페이지에서 렌더링할 view로 사용하기 위해 등장</li>
		<li>서블릿 페이지에서 할 수 있는 것은 모두 할 수 있따.</li>
		<li>컴파일되지 않기 때문에 오류를 IDE(이클립스)에 의존해야한다.</li>
		<li>정적리소스의 위치(webapp>하위)에 동적리소스가 위치하기 때문에 보안에 취약하다.</li>
	</ul>
	
	<h2>톰캣의 동적리소스 Java Servlet</h2>
	<p>톰캣에서 JVM으로 실행하는 동적리소스</p>
	<ul>
		<li>톰캣의 웹앱에서 동적리소스로 실행되는 타입의 클래스</li>
		<li>DD에 동적 리소스의 경로를 정의해야 동작 가능</li>
		<li>클라이언트의 요청과 응답을 처리할 수 있다.</li>
		<li>서블릿을 (동적리소스) 새로 생성하면 서버를 무조건 재시작해야 한다.</li>
		<li>서블릿을 수정해도 서버의 웹앱을 새롭게 배포 후 재시작해야 한다.(이클립스가 자동으로 해준다.)</li>
	</ul>
</body>
</html>