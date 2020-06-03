<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(session.getAttribute("id")!=null) { %>
1. <a href="/DataInput.jsp">정보입력</a>
<br><br>
2. <a href="/Data.do?action=list">목록조회</a>
<br><br>
3. <a href="/DataSearch.jsp">정보검색</a>
<br><br>
4. <a href="/Login.do">로그아웃</a>
<br><br><br><br>
<%Cookie[] cookies = request.getCookies(); %>
<%for(Cookie c : cookies) { %>
쿠키이름 : <%=c.getName()%>,
쿠키값 : <%=c.getValue()%>,
쿠키지속시간 : <%=c.getMaxAge()%>
<Br>
<% } %>
<% }else {response.sendRedirect("/Login.jsp");} %>
</body>
</html>