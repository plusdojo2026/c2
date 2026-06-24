<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/character.css">
<title>がんばろうあいぼう | キャラクター設定</title>
</head>
<body>

	<div class="top-decoration"></div>
  	<form action="${pageContext.request.contextPath}/HomeServlet"
         	 method="get">
        	<button id="backBtn" type="submit">
            	<b>戻る</b>
        	</button></form>

	<div class="chara-info">
    
    	<h1>キャラクターの選択</h1>
    	<p>犬と猫の中から好きなキャラを選んでね</p>
    </div>
    
	<div class="text-button">
		<p class="self-introduction"><br>
        食いしん坊ないぬの男の子！<br>
        チャームポイントはキリっとした眉毛とおやつがついてるバンダナ<br><br>
        きみががんばっている姿を、<br>
        いつも近くで応援しているよ！</p>
	
		 <div class="Button">   	
   			 <form action="${pageContext.request.contextPath}/CharaTransitionServlet"
          		method="post">

        	<button id="characterBtn" type="submit">
            <b>キャラクターの変更</b> 
            </button></form>		 	
   		 </div>
        
    	<div class="Button"> 
    		<form action="${pageContext.request.contextPath}/NameTransitionServlet"
         	 method="post">
        	<button id="nameBtn" type="submit">
            	<b>呼び方の設定</b>
        	</button></form>
        </div>
        	
        	 <p class="self-introduction"><br>
        マイペースなねこの女の子！<br>
        チャームポイントは綺麗な三毛とハートがついてるバンダナ<br><br>
        実はきみのがんばる姿が大好き<br>
        いつもそっと応援しているよ！</p>  
	</div>


	  	<div class="whole-image">
   		 <img src="<%=request.getContextPath()%>/images/dog.png"
         class="image-item">
         
    	<img src="<%=request.getContextPath()%>/images/cat.png"
         class="image-item">
         </div>
         
	
</body>
</html>