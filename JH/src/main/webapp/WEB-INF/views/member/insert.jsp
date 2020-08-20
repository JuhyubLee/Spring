<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Member ${message}</title>
<style>
#loadingbar {
	position: absolute;
	left: 50%;
	top: 50%;
	background: #ffffff;
}
</style>
</head>
<body>
	<h1>사원 정보 ${message eq "insert" ? "입력" : "수정" }</h1>
	<c:choose>
		<c:when test="${message eq 'insert'}">
			<form:form action="./${message}" method="post" modelAttribute="mem">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type=text name=userId id=userId></td>
						<td><button id=idCheck>중복검사</button></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type=text name=name></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type=password name=password></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type=text name=email></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type=text name=address></td>
					</tr>
					<tr>
						<th colspan=2><input type=submit
							value="${message eq 'insert' ? '입력' : '수정'}"> <input
							type=reset value="취소"></th>
				</table>
			</form:form>
		</c:when>

	</c:choose>
	<div id="loadingbar">
		<img src="../resources/images/load.gif" />
	</div>
	<script>
		var ck = false;
		$(function() {
			$("#loadingbar").hide();
			$("#idCheck").on("click", function() {
				if ($("#userId").val()) {
					$.ajax({
						async : 'true',
						url : "insert/idCheck",
						type : "post",
						data : {
							userId : $("#userId").val(),
							"${_csrf.parameterName}" : "${_csrf.token}"
						},
						dataType : "json",
						success : function(check) {
							if (check) {
								alert("중복되지 않는 아이디입니다.");
								$("#idCheck").remove();
								$("#userId").attr("readonly", true);
								ck = !ck;
							} else {
								alert("중복됩니다. 아이디를 다시 입력해주세요.");
							}
							return false;
						},
						error : function() {
							alert("시간이 너무 오래 걸립니다. 다시 시도해주세요.");
							return false;
						}
					});
				} else {
					alert("아이디를 입력하세요.");
				}
				return false;
			});
		}).ajaxStart(function() {
			$("#loadingbar").show();
		}).ajaxStop(function() {
			$("#loadingbar").hide();
		})
		function check() {
			if (!ck) {
				alert("아이디 중복검사를 실행해주세요.");
				return false;
			}
		}
	</script>
</body>
</html>