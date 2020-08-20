<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
</head>
<body>
<h2>회원 목록</h2>
<form action="list">
아이디 또는 이름 : <input type="search" name=word>
</form>
<table>
<tr>
<td>아이디</td>
<td>이름</td>
<td>이메일</td>
<td>주소</td>
<td>권한</td>
</tr>
<c:forEach var="member" items="${list}">
<tr>
<td><a href="${member.userId}">${member.userId}</a></td>
<td>${member.name}</td>
<td>${member.email}</td>
<td>${member.address}</td>
<c:set var="len" value="${fn:length(member.auth)}"/>
<td>${fn:substring(member.auth, 5, len)}</td>
</tr>
</c:forEach>
<tr>
<td colspan=5>
<h6>
[<a href="list?page=1">처음</a>]
<c:if test="${pageManager.nowBlock gt 1}">
	[<a href="list?page=${pageManager.startPage-1}">이전</a>]
</c:if>
<c:forEach var="i" begin="${pageManager.startPage}" end="${pageManager.endPage}">
[<a href="list?page=${i}">${i}</a>]
</c:forEach>
<c:if test="${pageManager.nowBlock < pageManager.totalBlock}">
	[<a href="list?page=${pageManager.endPage+1}">다음</a>]
</c:if>
</h6>
</td></tr>
</table>
</body>
</html>