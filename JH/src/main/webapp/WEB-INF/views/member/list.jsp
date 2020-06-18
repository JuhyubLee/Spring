<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
</head>
<body>
<h1>Member list</h1>
<table border=1>
<tr>
<th>userId</th>
<th>name</th>
<th>email</th>
<th>address</th>
<th>authorities</th>
<tr>
<c:forEach var="mem" items="${memList}">
<tr>
<td>${mem.userId}</td>
<td>${mem.name}</td>
<td>${mem.email}</td>
<td>${mem.address}</td>
<td>${mem.authorities}</td>
</tr>
</c:forEach>
</table>
<form action="view">
<input name=userId type="text"/>
<input type=submit value="검색">
</form>
</body>
</html>