<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>ȸ������ ȭ��</title>


</head>
<body>
	<!-- div ����, ������ �ٱ������� auto�� �ָ� �߾����ĵȴ�.  -->
	<div id="wrap">
		<br>
		<br> <b><font size="6" color="gray">ȸ������</font></b> <br>
		<br>
		<br>


		<!-- �Է��� ���� �����ϱ� ���� form �±׸� ����Ѵ� -->
		<!-- ��(�Ķ����) ������ POST ���, ������ �������� JoinPro.jsp -->
		<form id="sign">
			���̵� :<input type="text" name="id" maxlength="50"> <br>
			��й�ȣ :<input type="password" name="password" maxlength="50"><br>

			�̸�: <input type="text" name="name" maxlength="50"> <br>
			����: <input type="text" name="birth" maxlength="8" placeholder="0000/00/00" size="16"><br>
			����:	 <input type="text" name="phone" /><br>
			<br> 
			<input type="button" onclick="ajax()" value="����" /> 
			<input type="button" value="���" onclick="goLoginForm()">
		</form>
		
	</div>
</body>
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<!--  <script src="http://code.jquery.com/jquery.serializeObject.js" type="text/javascript"></script> -->
<script type="text/javascript">
	jQuery.fn.serializeObject = function() {
	    var obj = null;
	    try {
	        if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
	            var arr = this.serializeArray();
	            if(arr){ obj = {};
	                jQuery.each(arr, function() {
	                    obj[this.name] = this.value; });
	            }
	        }
	    }catch(e) {
	        alert(e.message);
	    }finally {}
	    return obj;
	}

	function ajax(){
		var formData = $("#sign").serializeObject();
		 console.log(JSON.stringify(formData));
		$.ajax({
			url:"sign",
			
			type:"POST",
			contentType:"application/json",
			data:JSON.stringify(formData),
			success:function(data){
				location.href = "/app";
			},error:function(xhr,status,error){
				
			}
		})
	}
	function goLoginForm() {
		location.href = "/app";
	}
</script>

</html>
