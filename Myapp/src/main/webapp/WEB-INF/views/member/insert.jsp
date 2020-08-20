<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member ${message eq 'insert' ? 'insert' : 'update'}</title>
</head>
<body>
<h2>회원 정보 ${message eq 'insert' ? '입력' : '수정'}</h2>
<form action="/myapp/member/${message eq 'insert' ? 'insert' : 'update'}" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table>
<tr>
<td>아이디</td><td><input type=text name=userId value="${mem.userId}" ${!empty mem ? 'readonly' : ''}></td>
</tr>
<tr>
<td>이름</td><td><input type=text name=name value="${mem.name}"></td>
</tr>
<tr>
<td>비밀번호</td><td><input type=password name=password></td>
</tr>
<tr>
<td>이메일</td><td><input type=text name=email value="${mem.email}"></td>
</tr>
<tr>
<td>주소</td><td><input type=text name=address value="${mem.address}"></td>
</tr>
</table>
<input type=submit value="${message eq 'insert' ? '입력' : '수정'}"><input type=reset value=취소>
</form>
</body>
</html>