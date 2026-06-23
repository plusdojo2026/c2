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

<div class="top-decoration"></div>

<form action="${pageContext.request.contextPath}/CharaServlet"
	method="get">
	<button id="backBtn" type="submit">
		<b>戻る</b>
	</button>
</form>


<div class="charachange-info">
	<img src="${pageContext.request.contextPath}/images/nikukyu_pink.png"  width="40">
	<h1>キャラクターの選択</h1>
	<img src="${pageContext.request.contextPath}/images/nikukyu_pink.png"  width="40">
</div>


<p class=charachange-info2>犬と猫の中から好きなキャラを選んでね</p>

<img src="${pageContext.request.contextPath}/images/nikukyu_pink.png" id=nikukyuu1 width="70">
	<div class="whole-image">
	<div class="box-outer">

	<div class="dog-image">
		<img src="<%=request.getContextPath()%>/images/dog.png"
			 width="200">
		<br>
		<p>　　　元気いっぱい！</p>
		<p>食いしん坊ないぬの男の子～♪</p>
		

		<form action="${pageContext.request.contextPath}/CharacterChangeServlet"
			  method="post">

			<button type="submit"
					name="dog"
					value="犬"
					id="animal-submit">
				犬
			</button>

		</form>
	</div></div>


	<div class="box-outer">
	<div class="cat-image">
		<img src="<%=request.getContextPath()%>/images/cat.png"
			 width="200">
		<br>

		<p>　　　のんびり気まま！</p>
		<p>お昼寝大好きな猫の女の子～♪</p>
		<form action="${pageContext.request.contextPath}/CharacterChangeServlet"
			  method="post">

			<button type="submit"
					name="cat"
					value="猫"
					id="animal-submit">
				猫
			</button>

		</form>
	</div></div>

</div>
<img src="${pageContext.request.contextPath}/images/nikukyu_pink.png"  id=nikukyuu2 width="70">
</body>
</html>