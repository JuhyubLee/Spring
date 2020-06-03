<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/ReDi.do" method=post>
이름 : <input type=text name=name><br>
<input type=hidden name=action value=RE>
<input type=submit value=Re>
</form>
<form action="/ReDi.do" method=post>
이름 : <input type=text name=name><br>
<input type=hidden name=action value=DI>
<input type=submit value=Di>
</form>
</body>
</html>