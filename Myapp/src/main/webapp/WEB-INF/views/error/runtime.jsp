<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
<h1>Error</h1>
<p>애플리케이션에 오류 발생. 담당자에게 연락하시려면 gctserf@gmail.com 또는 010-0000-0000으로 연락주세요.</p>
<script>
alert("${exception.message}");
history.back();
</script>
<!-- Failed URL : ${url}
	Exception : ${exception.message}
	<c:forEach items="${exception.stackTrace}" var="ste"> ${ste}
	</c:forEach>
 -->
</body>
</html>