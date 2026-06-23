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

<div class="top-decoration"></div>
 <form action="${pageContext.request.contextPath}/CharaServlet"
         	 method="get">
        	<button id="backBtn" type="submit">
            	<b>戻る</b>
        	</button></form>

	<div class="name-info">
    	
    	<h1>名前の変更</h1>
    	<p>キャラクターとあなたのあだ名を教えてね！</p><br>
    </div>
	
	<form id=adana action="${pageContext.request.contextPath}/NameServlet"
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
        	class="nickname-input"
            name="charaNickname"
            value="${user.charaNickname}"
            >
   		 </div>
   		 
   		 <p class="arrow">⇄</p> 

    	<div>
        	<label>あなたの呼び名を入力してください</label><br>
        	<input type="text"
        	class="nickname-input" 
            name="userNickname"
            value="${user.userNickname}"
            >
    	</div>
    </div>

		<div class="name-submit">
     		<button type="submit"  id="name-submit">
        		<b>登録する</b>
     		</button>
	     	<p>${message}</p>
			<p style=color:red><span id="msg"></span></p>
    	</div>

	</form>



<script>
'use strict';
document.getElementById('adana').onsubmit = function(event) {
	let charaNickname = document.getElementById('adana').elements['charaNickname'].value;
    let userNickname = document.getElementById('adana').elements['userNickname'].value;

    if (charaNickname === '' || userNickname === '') {
        document.getElementById('msg').textContent =
            '呼び名を登録してください。';
        event.preventDefault();
    }
}
</script>
</body>
</html>