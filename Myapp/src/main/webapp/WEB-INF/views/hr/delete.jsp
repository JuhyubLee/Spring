<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Delete</title>
</head>
<body>
<br>
삭제하려는 ${emp.firstName} ${emp.lastName}은 <br>
<h3>${count.empCount}</h3>명의 매니저이고 <br>
<h3>${count.deptCount}</h3>개의 부서를 책임지고 있습니다.<br>
<h3>정말 삭제하시겠습니까?</h3>
<form action="delete" method=post>
<input type=hidden name=empId value="${emp.employeeId}">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type=submit value="삭제">
<input type="button" value="취소" onclick="history.back(-1);">
</form>
</body>
</html>