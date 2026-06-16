<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | ログイン</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/LoginServlet"
		method="post">

		<p>
			<label>ユーザID</label><br> <input type="text" name="user_id">
		</p>

		<p>
			<label>パスワード</label><br> <input type="password" name="pw">
		</p>

		<button type="submit">ログイン</button>

	</form>

	<div>
		<a href="regist.jsp">新規登録はこちら</a>
	</div>

</body>
</html>