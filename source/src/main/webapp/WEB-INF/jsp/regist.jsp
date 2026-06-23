<!-- 新規会員登録 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login-regist.css">
<title>がんばろうあいぼう | 新規登録</title>
</head>
<body>

<div class="top-decoration"></div>
	<div class="login">
	 	<p class="top"><b>新規会員の登録</b></p>
	 
	 
	 	<div class="box-outer">
        <div class="box-inner">
			<form  id=regist action="${pageContext.request.contextPath}/RegistServlet" method="post">

    	<p>
        	<label for="id">ユーザID</label><br>
        	<input type="text" id="id" name="id">
   		</p>

   		 <p>
        	<label for="pw">パスワード</label><br>
        	<input type="password" id="pw" name="pw">
    	</p>

   			<button type="submit" class="submit">新規登録</button><br>
   			<p style="color:red"><span id="msg"></span></p><br>
    		<p style="color:red;">${error}</p>
	

		</form>
	</div></div></div>
	<script>
	'use strict'
	document.getElementById('regist').onsubmit = function(event) {
    let id = document.getElementById('regist').elements['id'].value;
    let pw = document.getElementById('regist').elements['pw'].value;

    if (id === '' || pw === '') {
        document.getElementById('msg').textContent =
            'ログインIDとパスワードを両方登録してください。';
        event.preventDefault();
    }
    
    if(pw.length<4){
    	document.getElementById('msg').textContent =
            'パスワードは４文字以上で登録してください。';
        event.preventDefault();
    }
    
}

	</script>

</body>
</html>