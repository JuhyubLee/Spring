<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Data.do">
검색하려는 사람의 이름을 입력하세요 :
<input type=text name=name>
<input type=hidden name=action value=search>
</form>
<%=session.getId() %>
</body>
</html>