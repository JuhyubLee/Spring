<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int total = 0;
for(int i=1; i<=100; i++){
	total = total+i;
}%>
1부터 100까지 더한 값은 : <%=total%>
</body>
</html>