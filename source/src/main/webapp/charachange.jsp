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
	
	<div class="charachange-info">
    	<br><br>
    	<h1>キャラクターの選択</h1>
    	<p>犬と猫の中から好きなキャラを選んでね</p>
    </div>
    
    <div class="whole-image">
    	<div class="dog-image">
    	 <img src="<%=request.getContextPath()%>/images/dog_akitainu.png"
             width="200">
             
    		<b><button type="submit" name="charaId" value="犬"　id="animal-submit"><b>
        
    </button>

    <button type="submit" name="charaId" value="2">
        <img src="<%=request.getContextPath()%>/images/pet_cat_sit.png"
             width="200">
    </button>

</body>
</html>