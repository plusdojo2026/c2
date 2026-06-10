<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="Button">
<button id="characterBtn" type="submit">キャラクターの変更</button>
<button id="nameBtn" type="submit">呼び方の設定</button>
</div>
<div class="image-chara">
  <img src="<%=request.getContextPath()%>/images/dog_akitainu.png" class="image-item">
  <img src="<%=request.getContextPath()%>/images/pet_cat_sit.png" class="image-item">
</div>
</body>
</html>