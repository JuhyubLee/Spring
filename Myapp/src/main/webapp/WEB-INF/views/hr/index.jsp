<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Menu</title>
</head>
<body>
1. <a href="count">총 사원 인원수</a><br>
<form action=count>
2. 부서별 인원수<br>
부서 번호 : <input type=text name=deptId><input type=submit value=검색>
</form>
3. <a href="list">전체 사원 목록</a><br>
4. <a href="insert">신규 사원 정보 입력</a><br>
5. <a href="getMaxSalary">부서별 최고 급여자 조회</a>
</body>
</html>