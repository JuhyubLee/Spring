<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</table>
<a href="update?userId=${mem.userId}">정보 수정</a><br>
<form action="delete" method="get">
<input type=hidden value=${mem.userId}/>
<input name="password" type="password"/>
<input type=submit value="정보 삭제">
</form>
</body>
</html>