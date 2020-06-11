<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload</title>
</head>
<body>
<form action="new" method=post enctype="multipart/form-data">
<select name="dir">
<option value="/">/
<option value="/images">이미지
<option value="/data">자료실
<option value="/spring">스프링
<option value="/general">공통
</select>
<input type=file name=file>
<input type=submit name=save>
<input type=reset name=cancel>
</form>
</body>
</html>