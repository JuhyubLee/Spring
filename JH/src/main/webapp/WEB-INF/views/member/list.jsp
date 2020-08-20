<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
</head>
<body>
<h1>Member list</h1>
<table border=1>
<tr>
<th>userId</th>
<th>name</th>
<th>email</th>
<th>address</th>
<th>authorities</th>
<tr>
<c:forEach var="mem" items="${memList}">
<tr>
<td><a href="view?userId=${mem.userId}">${mem.userId}</a></td>
<td>${mem.name}</td>
<td>${mem.email}</td>
<td>${mem.address}</td>
<c:set var="len" value="${fn:length(mem.auth)}"/>
<td>${fn:substring(mem.auth, 5, len)}</td>
</tr>
</c:forEach>
</tr>
</table>
<div>
	<c:if test="${pagination.curRange ne 1 }">
		<a href="list?page=1" onClick="fn_paging(1)">[처음]</a>
	</c:if>
	<c:if test="${pagination.curPage ne 1}">
		<a href="list?page=${pagination.startPage}" onClick="fn_paging('${pagination.prevPage}')">[이전]</a>
	</c:if>
	<c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
		<c:choose>
			<c:when test="${pageNum eq pagination.curPage}">
				<span style="font-weight: bold;"><a href="list?page=${pagination.curPage}" onClick="fn_paging('${pageNum}')">${pageNum }</a></span>
			</c:when>
			<c:otherwise>
				<a href="list?page=${pageNum}" onClick="fn_paging('${pageNum}')">${pageNum }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
		<a href="list?page=${pagination.nextPage}" onClick="fn_paging('${pagination.nextPage}')">[다음]</a>
	</c:if>
	<c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
		<a href="list?page=${pagination.endPage}" onClick="fn_paging('${pagination.pageCnt}')">[끝]</a>
	</c:if>

</div>
<div>
	총 게시글 수 : ${pagination.listCnt } / 총 페이지 수 : ${pagination.pageCnt } / 현재 페이지 : ${pagination.curPage } / 현재 블럭 : ${pagination.curRange } / 총 블럭 수 : ${pagination.rangeCnt }
</div>
<script type=text/javascript>
function fn_paging(curPage) {
location.href = "/member/list?page="+curPage;
}
</script>
<form action="view">
<input name=userId type="text"/>
<input type=submit value="검색">
</form>
</body>
</html>