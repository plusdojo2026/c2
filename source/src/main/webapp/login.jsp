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
	<div class="loginsize">
	 <p class="top"><b>ログイン</b></p>
	 
	<form action="${pageContext.request.contextPath}/LoginServlet"
		method="post">

		<p>
			<label for="id">ユーザID</label><br> <input type="text" id="id" name="id">
		</p>

		<p>
			<label for="pw">パスワード</label><br> <input type="password" id="pw" name="pw">
		</p>

		<button type="submit" class="submit">ログイン</button>

	</form>

	<div class="nav">
		<a href="regist.jsp">新規登録はこちら</a>
	</div>
	</div>
</body>
</html>