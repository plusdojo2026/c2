<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | 呼び方設定</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/NameTestServlet"
      method="post">
	
	<p>現在のキャラの呼び名：${user.charaNickname}</p>
	<p>現在のあなたの呼び名：${user.userNickname}</p>
	
	
    <div>
        <label>キャラの名前を入力してください</label>
        <input type="text"
               name="charaNickname"
               value="${user.charaNickname}"
               required>
    </div>

    <div>
        <label>あなたの呼び名を入力してください</label>
        <input type="text"
               name="userNickname"
                value="${user.userNickname}"
               required>
    </div>

    <button type="submit">
        登録する
    </button>
</form>

<p>${message}</p>

</body>
</html>