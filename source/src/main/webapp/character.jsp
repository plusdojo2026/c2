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


	<div class="chara-info">
    	<br><br>
    	<h1>キャラクターの選択</h1>
    	<p>犬と猫の中から好きなキャラを選んでね</p>
    </div>
    
	<div class="text-button">
		<p class="self-introduction">自己紹介</p>
	
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
        	
        	 <p class="self-introduction">自己紹介</p>  
	</div>


	  	<div class="whole-image">
   		 <img src="<%=request.getContextPath()%>/images/dog.png"
         class="image-item">
         
    	<img src="<%=request.getContextPath()%>/images/cat.png"
         class="image-item">
         </div>
	
</body>
</html>