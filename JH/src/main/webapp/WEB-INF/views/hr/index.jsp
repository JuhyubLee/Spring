<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Menu</title>
</head>
<body>
<form action="count" method="post">

<a href="<c:url value="/hr/count" />">1.총 사원 인원수</a><br>
2.부서별 인원수<br>

부서 번호 : <input type="text" name="deptId">
<input type=submit value=확인><br>

<a href="<c:url value="/hr/list" />">3.전체 사원 목록</a><br>

<a href="<c:url value="/hr/insert" />">4.신규 사원 정보 입력</a><br>

<a href="<c:url value="/hr/top" />">5.부서별 최고 급여자 조회</a><br>
</form>
</body>
</html>