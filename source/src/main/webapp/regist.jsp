<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/RegistServlet"
      method="post">

    <p>
        <label for="id">ユーザID</label><br>
        <input type="text" id="id" name="id">
    </p>

    <p>
        <label for="pw">パスワード</label><br>
        <input type="password" id="pw" name="pw">
    </p>

    <button type="submit">新規登録</button>

</form>

</body>
</html>