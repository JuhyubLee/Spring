<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member ${message}</title>
</head>
<body>
<h1>사원 정보 ${message eq "insert" ? "입력" : "수정" }</h1>
<c:choose>
<c:when test="${message eq 'insert'}">
<form:form action="./${message}" method="post"
modelAttribute="mem">
<table border=1>
<tr>
<td>아이디</td><td><input type=text name=userId></td>
</tr>
<tr>
<td>이름</td><td><input type=text name=name></td>
</tr>
<tr>
<td>비밀번호</td><td><input type=password name=password></td>
</tr>
<tr>
<td>이메일</td><td><input type=text name=email></td>
</tr>
<tr>
<td>주소</td><td><input type=text name=address></td>
</tr>
<tr>
<th colspan=2><input type=submit value="${message eq 'insert' ? '입력' : '수정'}">
<input type=reset value="취소"></th>
</table>
</form:form>
</c:when>
<c:when test="${message eq 'update'}">
<form action="./${message}" method=post>
<table border=1>
<tr>
<td>아이디</td>
<td><input type=text name=userId required value="${mem.userId}"
${empty mem ? "" : "readonly"}></td>
</tr>
<tr>
<td>이름</td>
<td><input type=text name=name value="${mem.name}"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type=password name=password value="${mem.password}"></td>
</tr>
<tr>
<td>이메일</td>
<td><input type=text name=email value="${mem.email}"></td>
</tr>
<tr>
<td>주소</td>
<td><input type=text name=address value="${mem.address}"></td>
</tr>
<tr>
<th colspan=2><input type=submit value="${message eq 'insert' ? '입력' : '수정'}">
<input type=reset value="취소"></th>
</tr>
</table>
</form>
</c:when>
</c:choose>
</body>
</html>