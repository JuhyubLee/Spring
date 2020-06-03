<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(request.getAttribute("tel")==null) { %>
입력하신 <%=request.getParameter("name")%> 님의
데이터가 없습니다.
<% }else { %>
이름 : <%=request.getParameter("name")%>,
연락처 : <%=request.getAttribute("tel")%>
<% } %>
<%=session.getId() %>
</body>
</html>