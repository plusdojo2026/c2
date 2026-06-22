<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/character-change.css">
<title>がんばろうあいぼう | キャラクター選択</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/CharaServlet"
         	 method="get">
        	<button id="backBtn" type="submit">
            	<b>戻る</b>
        	</button></form>
	
	<div class="charachange-info">
    	<br><br>
    	<h1>キャラクターの選択</h1>
    	<p>犬と猫の中から好きなキャラを選んでね</p>
    </div>
    
    
    
    <div class="whole-image">
    	<div class="dog-image">
    	 <img src="<%=request.getContextPath()%>/images/dog.png"
             width="200">
             <br> 
             <form action="${pageContext.request.contextPath}/CharaChangeTransitionServlet"
          		method="post">
    		<button type="submit" name="dog"value="犬"id="animal-submit">
        	犬
   			 </button></form></div>

	 <div class="cat-image">
     		<img src="<%=request.getContextPath()%>/images/cat.png"
             width="200">
             <br> 
             <form action="${pageContext.request.contextPath}/CharaChangeTransitionServlet"
          		method="post">
            <button type="submit" name="cat" value="猫" id="animal-submit">
       		猫
    		</button></form></div></div>

</body>
</html>