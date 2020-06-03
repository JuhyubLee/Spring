<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Data.do" method=post>
이름 : <input type=text name=name><br>
연락처 : <input type=text name=tel><br>
<input type=submit>
</form>
<%=session.getId() %>
</body>
</html>