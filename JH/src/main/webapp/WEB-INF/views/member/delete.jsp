<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Delete</title>
</head>
<body>
<h3>정말 삭제하시겠습니까?</h3>
<form action="delete" method="post">
<input type=submit value="정보 삭제">
<input type="button" value="취소" onclick="history.back(-1);">
</form>
</body>
</html>