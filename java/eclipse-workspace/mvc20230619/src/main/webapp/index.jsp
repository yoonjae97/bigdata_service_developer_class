<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mvc student</title>
</head>
<body>
	<h1>mvc c##smart01.student 관리자 웹앱 구현</h1>
	<h2>Model View Controller</h2>
	<h3>Model</h3>
	<ul>
		<li>클라이언트에게 실제로 서비스될 내역</li>
		<li>보통 웹앱은 db 저장된 내역을 보여주거나 수정하거나 삭제하거나 등록하는 서비스를 제공한다.</li>
		<li>db는 아무나 접속해서 수정 삭제하면 위험하기 때문에 보통 dql이나 dml
		권한이 있는 게정으로만 서비스를 제공</li>
		<li>SQL은 고급 개발자만 사용가능하기 때문에 일반 유저가 모델을 직접 접근 조회할 수 없다.</li>
	</ul>
	<h2>View</h2>
	<ul>
		<li>웹에서 유저에게 제공하는 인터페이스로 html, css, js로 되어 있다.</li>
		<li>html은 정적리소스기 때문에 db의 내역이 실시간으로 바뀌는 것을 반영할 수 없다.</li>
		<li>동적리소스에서 db내역을 가져오면 view를 렌더링하거나 포워드하는 방법으로 html을 응답한다.</li>
		<li>톰캣의 서블릿은 jsp를 포워드하는 방식으로 응답한다.</li>
		<li>forward :서블릿의 응답이 끝나지 않고 jsp에게 위임되는 형태 (보안에 취약해서 현재는 사용되지 않는다.)</li>
		<li>rendering : 동적리소스가 view가 될 파일을 실행(렌더) 후 반환되는 html을 응답하는 구조</li>
	</ul>
	<h3>Controller</h3>
	<ul>
		<li>요청처리와 응답처리만 하는 동적리소스를 Controller라 한다.</li>
		<li>Servlet에서 db접속 코드와 html 출력을 제외 위임하면 Controller 가 된다.</li>
		<li></li>
	</ul>


</body>
</html>