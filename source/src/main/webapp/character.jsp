<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | キャラクター設定</title>
</head>
<body>

<div class="Button">

    <form action="${pageContext.request.contextPath}/CharaTransitionServlet"
          method="post">
        <button id="characterBtn" type="submit">
            キャラクターの変更
        </button>
    </form>

    <form action="${pageContext.request.contextPath}/NameTransitionServlet"
          method="post">
        <button id="nameBtn" type="submit">
            呼び方の設定
        </button>
    </form>

</div>

<div class="image-chara">
    <img src="<%=request.getContextPath()%>/images/dog_akitainu.png"
         class="image-item">

    <img src="<%=request.getContextPath()%>/images/pet_cat_sit.png"
         class="image-item">
</div>

</body>
</html>