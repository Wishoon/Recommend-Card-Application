<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<a href="/home">뒤로가기</a>
</body>
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
		$.ajax({
			url:"/app/card/select",
			type:"POST",
			contentType:"application/json",
			success:function(data){
				for (i=0;i<data.length;i++){
					document.write("<p>"+ data[i].card_CARD_NAME+ "</p><br>");
					document.write("<p>hello</p>")
				}
			},error:function(xhr,status,error){
				
			}
		})
	function goLoginForm() {
		location.href = "/app/home";
	}
</script>
</html>