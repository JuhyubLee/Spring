<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
</head>
<body>
<form action="insert" method="post">
<table border=1>
<td>아이디</td><td><input type="text" name="userId"></td>
<tr>
<td>이름</td><td><input type="text" name="name"></td>
</tr>
<tr>
<td>비밀번호</td><td><input type="password" name="password"></td>
</tr>
<tr>
<td>이메일</td><td><input type="text" name="email"></td>
</tr>
<tr>
<td>주소</td><td><input type="text" name="address"></td>
</tr>
<tr>
<th colspan=2><input type=submit value="입력">
<input type=reset value="취소"></th>
</tr>
</table>
</form>
</body>
</html>