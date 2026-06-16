<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

			<c:forEach var="mission" items="${missions}">
				<c:if test="${mission != null}">
					<div class="mission-row">

						<p>${mission}</p>

						<button type="button" class="complete-btn"
							data-mission="${mission}" data-complete="1">できた</button>

						<button type="button" class="complete-btn"
							data-mission="${mission}" data-complete="0">できなかった</button>

					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<div class="image-home">
		<img src="<%=request.getContextPath()%>/images/pet_cat_sit.png"
			class="image-item">
	</div>

	<div class="home-menu">
		<form action="${pageContext.request.contextPath}/HomeMissionServlet"
			method="post">
			<button type="submit">ミッション設定</button>
		</form>

		<form action="${pageContext.request.contextPath}/HomeDailyServlet"
			method="post">
			<button type="submit">頑張りの確認</button>
		</form>

		<form action="${pageContext.request.contextPath}/HomeCharaServlet"
			method="post">
			<button type="submit">キャラクター設定</button>
		</form>
	</div>

	<script>
const radarData = [
    ${radarData[0]},
    ${radarData[1]},
    ${radarData[2]},
    ${radarData[3]},
    ${radarData[4]}
];

console.log("JSP radarData=", radarData);
</script>

	<script src="<%=request.getContextPath()%>/js/home.js"></script>
</body>
</html>