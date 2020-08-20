<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
<sec:authorize access="isAnonymous()">
<a href="member/insert">회원 가입</a><br>
<a href="login">로그인</a><br>
<a href="hr/index">인사 관리</a><br>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
<sec:authentication property='principal.userId'/>님 안녕하세요.<br>
<a href="file">파일 업/다운</a><br>
<a href="member/<sec:authentication property='principal.userId'/>">마이 페이지</a><br>
<a href="hr/index">인사 관리</a><br>
<form action=logout method=post>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type=submit value=로그아웃>
</form>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
<sec:authentication property='principal.userId'/>님 안녕하세요.<br>
<a href="file">파일 업/다운</a><br>
<a href="member/list">회원 목록 관리</a><br>
<form action=logout method=post>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type=submit value=로그아웃>
</form>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasRole('ROLE_MASTER')">
Master님 안녕하세요.<br>
<a href="file">파일 업/다운</a><br>
<a href="member/list">회원 목록 관리</a><br>
<form action=logout method=post>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type=submit value=로그아웃>
</form>
</sec:authorize>
</body>
</html>