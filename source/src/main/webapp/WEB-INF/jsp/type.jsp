<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/type.css">
<title>がんばろうあいぼう | タイプ選択</title>



</head>
<body>
	<div class="top-decoration"></div>

	<form
		action="${pageContext.request.contextPath}/CharacterChangeServlet"
		method="get">
		<button id="backBtn" type="submit">
			<b>戻る</b>
		</button>
	</form>

	<div class=type-info>

		<br> <br>
		<h1>タイプの選択</h1>
		<p>4つの生活タイプの中からあなたに合うものを選んでね</p>


		<form action="${pageContext.request.contextPath}/TypeServlet"
			method="post">

			<button class="type-button1" type="submit" name="typeId" value="1">
				<img src="${pageContext.request.contextPath}/images/dog_sun.png"  width="100"><br>
				<b>朝型 ✕ 晴れ</b><br><br><br>
				7時起床、23時就寝に近い生活の方が外出する日
				</button>

			<button class="type-button2" type="submit" name="typeId" value="2">
				<img src="${pageContext.request.contextPath}/images/cat_cloudy.png"  width="100"><br>
				<b>朝型 ✕ 曇り</b><br><br><br>
				7時起床、23時就寝に近い生活の方が家にいる日</button>

			<button class="type-button3" type="submit" name="typeId" value="3">
				<img src="${pageContext.request.contextPath}/images/dog_sun.png"  width="100"><br>
				<b>夜型 ✕ 晴れ</b><br><br><br>
				23時起床、15時就寝に<br>近い生活の方が外出する日</button>

			<button class="type-button4" type="submit" name="typeId" value="4">
				<img src="${pageContext.request.contextPath}/images/cat_cloudy.png"  width="100"><br>
				<b>夜型 ✕ 曇り</b><br><br><br>
				23時起床、15時就寝に<br>近い生活の方が家にいる日</button>

		</form>
	</div>

	

</body>
</html>