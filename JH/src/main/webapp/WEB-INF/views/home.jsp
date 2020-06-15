<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="hr/index">인사 관리</a><br>
<sec:authorize access="isAnonymous()">
<a href="login">로그인</a><br>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<form action=logout method=post>
<input type=submit value=로그아웃>
</form>
</sec:authorize>
<a href="member/insert">회원 가입</a>
</body>
</html>
