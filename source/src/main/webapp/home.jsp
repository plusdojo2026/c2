<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | ホーム</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/aibou.css">
</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<div class="chart-and-text">
		<canvas id="radarChart" width="400" height="400"></canvas>

		<div class="mission-area">
			<h1>今日のミッション</h1>

			<div class="mission-row">
				<p>洗濯をした</p>
				<button type="submit">できた</button>
				<button type="submit">できなかった</button>
			</div>

			<div class="mission-row">
				<p>ご飯を自分で作った</p>
				<button type="submit">できた</button>
				<button type="submit">できなかった</button>
			</div>

			<div class="mission-row">
				<p>早く寝れた</p>
				<button type="submit">できた</button>
				<button type="submit">できなかった</button>
			</div>
		</div>
	</div>
		<div class="image-home">
			<img src="<%=request.getContextPath()%>/images/pet_cat_sit.png"
				class="image-item">
		</div>

		<div class="home-menu">
			<form action="MissionServlet" method="post">
				<button type="submit">ミッション設定</button>
			</form>

			<form action="CalendarServlet" method="post">
				<button type="submit">頑張りの確認</button>
			</form>

			<form action="CharacterServlet" method="post">
				<button type="submit">キャラクター設定</button>
			</form>
		</div>

	<script src="<%=request.getContextPath()%>/js/home.js"></script>
</body>
</html>