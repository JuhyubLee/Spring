<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(request.getMethod().equals("POST")){ %>
<%request.setCharacterEncoding("UTF-8"); %>
아이디 : <%=request.getParameter("id") %><br>
이름 : <%=request.getParameter("name") %>
<% } %>
</body>
</html>