<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<sec:authorize access="isAnonymous()">
${message}
<form action="loginCheck" method="post">
아이디 : <input type=text name=id><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=로그인>
</form> 
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="user"/>
${user.username}님 안녕하세요.<br>
<a href="<c:url value="file"/>">파일 업/다운</a><br>
<a href="<c:url value="/member/view?userId=${user.username}"/>">마이페이지</a><br>
<a href="<c:url value="hr/list"/>">인사 관리</a>
<form action="${pageContext.request.contextPath}/logout" method="post">
<input type=submit value="로그아웃">
</form></sec:authorize>
</body>
</html>