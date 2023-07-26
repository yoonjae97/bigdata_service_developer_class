<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<c:if test="${logStatus=='Y'}">
	<p><a onclick="memberUpdate()">회원정보수정</a></p>
</c:if>
<script type="text/javascript">
    $(document).ready(function () {
        // ModelAndView로 전달된 alertMessage를 확인하고 있으면 alert 메시지를 띄웁니다.
        var alertMessage = "${alertMessage}";
        if (alertMessage) {
            alert(alertMessage);
        }
    });
</script>
</body>
</html>
