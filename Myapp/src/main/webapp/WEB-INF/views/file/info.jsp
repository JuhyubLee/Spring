<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Info</title>
<script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<c:set var="len" value="${fn:length(file.fileName)}"/>
<c:set var="fileType" value="${fn:toUpperCase(fn:substring(file.fileName, len-4, len))}" />
<c:choose>
<c:when test="${(fileType eq '.JPG') or (fileType eq 'JPEG') or (fileType eq '.PNG') or (fileType eq '.GIF')}">
파일 이름 : <img src='<c:url value="/img/${file.fileId}"/>' width="100" class="img-thumbnail"><br>
<a href="../img/${file.fileId}">${file.fileName}</a><br>
</c:when>
<c:otherwise>
파일 이름 : <img src='<c:url value="/resources/images/straw.jpg"/>' width="100" class="img-thumbnail">
<a href="../pds/${file.fileId}">${file.fileName}</a><br>
</c:otherwise>
</c:choose>
파일 디렉토리 : ${file.directoryName}<br>
파일 크기 : <fmt:formatNumber value="${file.fileSize/1024}" pattern="#,###" />KB<br>
파일 유형 : ${file.fileContentType}<br>
파일 업로드 날짜 : ${file.fileUploadDate}<br>
<a href='<c:url value="/file/delete/${file.fileId}" />' class="delete">파일 삭제</a>
<script type="text/javascript">
$(document).ready(function(){
	$(".delete").click(function(){
		if(confirm("이 작업은 되돌릴 수 없습니다. 파일을 삭제하시겠습니까?")){
			return true;
		}else{
			return false;
		}
	})
});
</script>
</body>
</html>