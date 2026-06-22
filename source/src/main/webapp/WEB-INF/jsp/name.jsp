<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/name.css">
<title>がんばろうあいぼう | 呼び方設定</title>
</head>
<body>

 <form action="${pageContext.request.contextPath}/CharaServlet"
         	 method="get">
        	<button id="backBtn" type="submit">
            	<b>戻る</b>
        	</button></form>

	<div class="name-info">
    	
    	<h1>名前の変更</h1>
    	<p>キャラクターとあなたのあだ名を教えてね！</p><br>
    </div>
	
	<form action="${pageContext.request.contextPath}/NameServlet"
      method="post">
	
	<div class="nowname">
         <h3>現在のニックネーム</h3>
		 <p>キャラ：${user.charaNickname}</p>
		 <p>あなた：${user.userNickname}</p>
	</div>
	
	
	<br><h3 class="name-info">新しくつけるニックネーム</h3>
	 <div class="name-text">
    	<div>
        	<label>キャラの名前を入力してください</label><br>
        	<input type="text"
               name="charaNickname"
               value="${user.charaNickname}"
               required id="text">
   		 </div>
   		 
   		 <p class="arrow">⇄</p> 

    	<div>
        	<label>あなたの呼び名を入力してください</label><br>
        	<input type="text"
               name="userNickname"
                value="${user.userNickname}"
               required id="text">
    	</div>
    </div>

		<div class="name-submit">
     		<button type="submit"  id="name-submit">
        		登録する
     		</button>
    	</div>
	</form>

<p>${message}</p>
	 
</body>
</html>