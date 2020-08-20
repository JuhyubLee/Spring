<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Info</title>
</head>
<body>
<h2>회원 상세 정보</h2>
<table border=1>
<tr>
<td>아이디</td><td>${mem.userId}</td>
</tr>
<tr>
<td>이름</td><td>${mem.name}</td>
</tr>
<tr>
<td>이메일</td><td>${mem.email}</td>
</tr>
<tr>
<td>주소</td><td>${mem.address}</td>
</tr>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
<tr>
<td>권한</td><td>${mem.auth}</td>
</tr>
</sec:authorize>
</table>
<a href="update/${mem.userId}">정보 수정</a><br><br>
<form action="delete" method=post>
<sec:csrfInput/>
<input type=hidden name=userId value="${mem.userId}">
비밀번호 입력 : <input type=password name=password>
<input type=submit value="회원 탈퇴">
</form>
<br>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
<form action="enable" method="post">
<sec:csrfInput/>
<input type=hidden name=userId value="${mem.userId}">
<a href="list">회원 목록</a><br><br>
<input type=checkbox name=enable value=1>
<input type=submit value="${mem.isEnabled() ? '계정 비활성' : '계정 활성' }">
</form>
</sec:authorize>
<br>
<sec:authorize access="hasRole('ROLE_MASTER')">
<form action="updateAuth" method="post">
<sec:csrfInput/>
<input type=hidden name=userId value="${mem.userId}">
<h3>권한 변경</h3>
${authMessage}
<select name=auth>
<option value="ROLE_USER">USER</option>
<option value="ROLE_ADMIN">ADMIN</option>
<option value="ROLE_MASTER">MASTER</option>
</select>
<input type=submit value=변경>
</form>
</sec:authorize>
</body>
</html>