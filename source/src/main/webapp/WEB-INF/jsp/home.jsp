<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | ホーム</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/home.css">
</head>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<div class="top-decoration"></div>
<div class="chart-and-text">

	<div class="radar-area">
		<canvas id="radarChart" width="400" height="350"></canvas>
	</div>

	<div class="mission-wrapper">

		<div class="mission-area">
			<h1>今日のミッション</h1>

			<c:forEach var="mission" items="${missions}" varStatus="st">
				<c:if test="${mission != null}">
					<div class="mission-row">
						<p>${mission}</p>

						<button type="button"
							class="complete-btn ${completes[st.index] == 1 ? 'active' : ''}"
							data-mission="${mission}" data-complete="1">できた</button>

						<button type="button"
							class="complete-btn ${completes[st.index] == 0 ? 'active' : ''}"
							data-mission="${mission}" data-complete="0">できなかった</button>
					</div>
				</c:if>
			</c:forEach>
		</div>

		<!-- ミッションエリア右側のヘルプアイコン -->
		<span class="help-icon" id="helpBtn">ⓘ</span>

	</div>

	<!-- ポップアップ -->
	<div id="helpModal" class="modal">
		<div class="modal-content">

			<span class="close-btn">&times;</span>

			<h2>利用方法</h2>
			<div class="help-item">
				<img src="images/help_screen.png" alt="画面遷移">

				<div>
					<h3>画面遷移</h3>
					<p>各ボタンを押すことで、それぞれの画面へ移動できます。</p>
					<ul>
						<li>ミッション設定ボタン → ミッション設定画面</li>
						<li>頑張りの確認ボタン → 頑張りの確認画面</li>
						<li>キャラクター設定ボタン → キャラクター設定画面</li>
					</ul>
				</div>
			</div>

			<div class="help-item">
				<img src="images/help_mission.png" alt="ミッション">
				<div>
					<h3>ミッション達成</h3>
					<p>ミッションを達成したら「できた」を押してください。</p>
				</div>
			</div>

			<div class="help-item">
				<img src="images/help_chart.png" alt="レーダーチャート">
				<div>
					<h3>レーダーチャート</h3>
					<p>実績に応じて能力値が変化します。</p>
				</div>
			</div>

			<div class="help-item">
				<img src="images/help_season.jpeg" alt="季節背景">
				<div>
					<h3>部屋・旅行先の変化</h3>
					<p>ミッションを達成していくと、 キャラクターが住む部屋や旅行先が変化します。</p>
				</div>
			</div>

			<div class="help-item">
				<img src="images/help_outside.jpeg" alt="旅行背景">
				<div>
					<h3>景色の変化</h3>
					<p>春・夏・秋・冬と昼と夜で背景も変化するため、 季節の移り変わりも楽しめます。</p>
				</div>
			</div>

		</div>
	</div>
</div>

<div class="character-area">

	<img src="<%=request.getContextPath()%>/images/${charaImage}"
		class="image-item">

	<div class="right-area">

		<div class="home-menu">

			<div class="menu-top">
				<form action="${pageContext.request.contextPath}/HomeMissionServlet"
					method="post">

					<button type="submit" class="image-button">
						<img src="<%=request.getContextPath()%>/images/btn/mission.png"
							alt="ミッション設定">
					</button>

				</form>
			</div>

			<div class="menu-bottom">
				<form action="${pageContext.request.contextPath}/HomeDailyServlet"
					method="post">

					<button type="submit" class="image-button">
						<img src="<%=request.getContextPath()%>/images/btn/calendar.png"
							alt="頑張りの確認">
					</button>

				</form>

				<form action="${pageContext.request.contextPath}/HomeCharaServlet"
					method="post">

					<button type="submit" class="image-button">
						<img src="<%=request.getContextPath()%>/images/btn/character.png"
							alt="キャラクター設定">
					</button>

				</form>
			</div>

		</div>

		<div class="right-area">


			<div class="chara-balloon">${charaNickname}</div>

			<div class="chara-message">${charaMessage}</div>


		</div>



	</div>
</div>

<script>
const radarData = [
    ${radarData[0]},
    ${radarData[1]},
    ${radarData[2]},
    ${radarData[3]},
    ${radarData[4]}
];

</script>
<form action="${pageContext.request.contextPath}/LogoutServlet"
	method="get" class="logout-form"
	onsubmit="return confirm('本当にログアウトしますか？');">

	<button type="submit" class="logout-btn">ログアウト</button>

</form>

<body class="home-body"
	style="background-image:url('${pageContext.request.contextPath}/images/bg/${backgroundImage}');">
	<script src="<%=request.getContextPath()%>/js/home.js"></script>
</body>
</html>