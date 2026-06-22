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
			<form action="${pageContext.request.contextPath}/RegistServlet" method="post">

    	<p>
        	<label for="id">ユーザID</label><br>
        	<input type="text" id="id" name="id">
   		</p>

   		 <p>
        	<label for="pw">パスワード</label><br>
        	<input type="password" id="pw" name="pw">
    	</p>

   			 <button type="submit" class="submit">新規登録</button>

		</form>
	</div></div></div>

</body>
</html>