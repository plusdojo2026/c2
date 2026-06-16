<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | キャラクター選択</title>
</head>
<body>
	
    <button type="submit" name="charaId" value="1">
        <img src="<%=request.getContextPath()%>/images/dog_akitainu.png"
             width="200">
    </button>

    <button type="submit" name="charaId" value="2">
        <img src="<%=request.getContextPath()%>/images/pet_cat_sit.png"
             width="200">
    </button>

</body>
</html>