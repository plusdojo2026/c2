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
						<img src="<%=request.getContextPath()%>/images/mission.png"
							alt="ミッション設定">
					</button>

				</form>
			</div>

			<div class="menu-bottom">
				<form action="${pageContext.request.contextPath}/HomeDailyServlet"
					method="post">

					<button type="submit" class="image-button">
						<img src="<%=request.getContextPath()%>/images/calendar.png"
							alt="頑張りの確認">
					</button>

				</form>

				<form action="${pageContext.request.contextPath}/HomeCharaServlet"
					method="post">

					<button type="submit" class="image-button">
						<img src="<%=request.getContextPath()%>/images/character.png"
							alt="キャラクター設定">
					</button>

				</form>
			</div>

		</div>

		<div class="chara-message">${charaMessage}</div>

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
	style="background-image:url('${pageContext.request.contextPath}/images/${backgroundImage}');">
	<script src="<%=request.getContextPath()%>/js/home.js"></script>
</body>
</html>