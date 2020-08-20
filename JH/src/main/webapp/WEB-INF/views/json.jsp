<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<button id=btn1>XMLHttpRequest 전송</button>
<button id=btn2>jQuery 전송</button>
<div id=result></div>
<script>
var jsonVO = {name:"ABC",age:31};
$("#btn1").on("click",function(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === xhr.DONE){
			if(xhr.status === 200 || xhr.status === 201){
				document.getElementById("result").innerText=xhr.responseText;
			}
		}
	}
	xhr.open('get','json/ex?name=EFG&age=31');
	xhr.setRequestHeader('content-type','application/json');
	xhr.send();
});
$('#btn2').on("click",function(){
	$.ajax({
		url : "json/ex",
		type : "get",
		data : jsonVO,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(result){
			$("#result").text(result);
		},
		error : function(error){
			alert(error.statusText);
		}
	});
});
</script>

</body>
</html>