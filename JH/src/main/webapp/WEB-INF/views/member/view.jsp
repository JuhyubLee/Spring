<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member View</title>
</head>
<body>
<h1>회원 상세 정보</h1>
<table border="1">
<tr>
<th>아이디</th>
<td>${mem.userId}</td>
</tr>
<tr>
<th>이름</th>
<td>${mem.name}</td>
</tr>
<tr>
<th>이메일</th>
<td>${mem.email}</td>
</tr>
<tr>
<th>주소</th>
<td>${mem.address}</td>
</tr>
<tr>
<th>권한</th>
<td>${mem.authorities}</td>
</tr>
</table>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
<form action=updateEna method=post>
<input name="userId" type=hidden value=${mem.userId}>
<c:choose>
	<c:when test="${mem.enabled == true}">
		<input type=submit value="비활성화">
	</c:when>
	<c:when test="${mem.enabled == false}">
		<input type=submit value="활성화">
	</c:when>
</c:choose>
</form>

<form action=updateAu>
<input name="userId" type=hidden value=${mem.userId}>
<select name="selectAu">
<option value="ROLE_USER">USER
<option value="ROLE_ADMIN">ADMIN
<option value="ROLE_MASTER">MASTER
</select><input type=submit value="변경"><p>
</form>
</sec:authorize>
<a href="update?userId=${mem.userId}">정보 수정</a><br>
<form action="delete" method="get">
<input name="userId" type=hidden value=${mem.userId}>
비밀번호 입력 : <input name="password" type="password">
<input type=submit value="회원 탈퇴">
</form>
</body>
</html>