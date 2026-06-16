<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | 呼び方設定</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/NameServlet"
      method="post"></form>

    <div>
        <label>キャラの名前を入力してください</label>
        <input type="text"
               name="charaNickname"
               required>
    </div>

    <div>
        <label>あなたの呼び名を入力してください</label>
        <input type="text"
               name="userNickname"
               required>
    </div>

    <button type="submit"
            name="regist"
            value="登録">
        登録する
    </button>


</body>
</html>