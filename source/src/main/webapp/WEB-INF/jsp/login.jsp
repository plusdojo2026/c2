<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login-regist.css">
<title>がんばろうあいぼう | ログイン</title>
</head>
<body>
	<div class="top-decoration"></div>

	<div class="login">
		<p class="top">
			<b>ログイン</b>
		</p>

		<div class="box-outer">
        <div class="box-inner">
		<form id=login action="${pageContext.request.contextPath}/LoginServlet"
			method="post">

			<p>
				<label for="id">ユーザID</label><br> <input type="text" id="id"
					name="user_id">
			</p>

			<p>
				<label for="pw">パスワード</label><br> <input type="password"
					id="pw" name="pw">
			</p>

			<button type="submit" name="login" class="submit">ログイン</button>
			 <span style=color:red><span id="msg"></span></span>
			</form>	
			<br>
		<div class="nav">
			<a href="${pageContext.request.contextPath}/RegistServlet">
				新規登録はこちら </a>
		</div>
		</div>
		</div>
		  

	</div>
	
	<script>
	'use strict'
	document.getElementById('login').onsubmit = function(event) {
    let id = document.getElementById('login').elements['user_id'].value;
    let pw = document.getElementById('login').elements['pw'].value;

    if (id === '' || pw === '') {
        document.getElementById('msg').textContent =
            'ログインIDとパスワードを入力してください。';
        event.preventDefault();
    }
}
	</script>
	<!-- JavaScript（ここまで） -->

</body>
</html>
