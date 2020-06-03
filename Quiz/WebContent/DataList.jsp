<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="lab.web.vo.DataVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%ArrayList<DataVO> list = (ArrayList<DataVO>)
request.getAttribute("list");%>
<%for(DataVO data : list) { %>
이름 : <%=data.getName()%>,
연락처 : <%=data.getTel()%>,
나이 : <%=data.getAge()%><br>
<% } %>
</body>
</html>