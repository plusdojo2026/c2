<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/LoginServlet"
      method="post">

    <p>
        <label for="id">ユーザID</label><br>
        <input type="text" id="id" name="id">
    </p>

    <p>
        <label for="pw">パスワード</label><br>
        <input type="password" id="pw" name="pw">
    </p>

    <button type="submit">ログイン</button>

</form>

<div>
    <a href="regist.jsp">新規登録はこちら</a>
</div>

</body>
</html>