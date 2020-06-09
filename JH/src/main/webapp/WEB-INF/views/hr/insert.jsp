<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee ${message}</title>
</head>
<body>
<h1>사원 정보 ${message eq "insert" ? "입력" : "수정" }</h1>
<form action="./${message}" method=post>
<table border=1>
<tr>
<th>Employee_id</th>
<td><input type=text name=employeeId required
value="${emp.employeeId}"
${empty emp ? "" : "readonly"}></td>
</tr>
<tr>
<th>First_name</th>
<td><input type=text name=firstName value="${emp.firstName}"></td>
</tr>
<tr>
<th>Last_name</th>
<td><input type=text name=lastName required
value="${emp.lastName}"></td>
</tr>
<tr>
<th>Email</th>
<td><input type=text name=email required value="${emp.email}" ${empty
emp ? "" : "readonly"}></td>
</tr>
<tr>
<th>Phone_number</th>
<td><input type=text name=phoneNumber value="${emp.phoneNumber}"></td>
</tr>
<tr>
<th>Hire_date</th>
<td><input type=date name=hireDate value="${emp.hireDate}"></td>
</tr>
<tr>
<th>Job_id</th>
<td><select name=jobId>
<c:forEach var="job" items="${jobList}">
<option value="${job.jobId}" ${emp.jobId eq job.jobId ? "selected" :
"" }>${job.jobTitle}</option>
</c:forEach>
</select></td>
</tr>
<tr>
<th>Salary</th>
<td><input type=text name=salary value="${emp.salary}"></td>
</tr>
<tr>
<th>Commission_pct</th>
<td><input type=number name=commissionPct step=0.05 min=0 max=0.95
value="${emp.commissionPct}"></td>
</tr>
<tr>
<th>Manager_id</th>
<td><select name=managerId>
<c:forEach var="man" items="${manList}">
<option value="${man.managerId}" ${emp.managerId eq man.managerId ? 
"selected" : "" }>${man.managerName}</option>
</c:forEach>
</select></td>
</tr>
<tr>
<th>Department_id</th>
<td><select name=departmentId>
<c:forEach var="dept" items="${deptList}">
<option value="${dept.departmentId}" ${emp.departmentId eq 
dept.departmentId ? "selected" : "" }>${dept.departmentName}</option>
</c:forEach>
</select></td>
</tr>
<tr>
<th colspan=2><input type=submit value="${message eq "insert" ? "입력" : "수정"}">
<input type=reset value="취소"></th>
</tr>
</table>
</form>
</body>
</html>