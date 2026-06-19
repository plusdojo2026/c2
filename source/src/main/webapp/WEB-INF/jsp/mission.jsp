<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>がんばろうあいぼう | ミッション設定</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mission.css">

</head>
<body>

	<form action="${pageContext.request.contextPath}/HomeServlet"
		method="get" class="back-form">

		<button type="submit" class="back-btn">戻る</button>

	</form>
	<div class="main-area">

		<div class="speech">${comment}</div>

		<div class="chara-area">

			<img src="${pageContext.request.contextPath}/images/${charaImage}">
		</div>

		<div class="theme-box">

			<h2>テーマ選択</h2>

			<div class="theme-buttons">
				<button type="button" onclick="loadMission('生活')">生活</button>
				<button type="button" onclick="loadMission('勉強')">勉強</button>
				<button type="button" onclick="loadMission('運動')">運動</button>
				<button type="button" onclick="loadMission('お金')">お金</button>
				<button type="button" onclick="loadMission('趣味')">趣味</button>
			</div>

		</div>

	</div>

	<!-- ポップアップ -->
	<div id="missionPopup" class="popup">

		<div class="popup-content">

			<span class="close" onclick="closePopup()"> × </span>

			<h3>今日のミッション</h3>

			<!-- JSがここにミッションを追加 -->
			<div id="missionList"></div>

			<form id="missionForm"
				action="${pageContext.request.contextPath}/MissionChangeServlet"
				method="post">

				<input type="hidden" id="missionData" name="missionData">

				<div class="popup-btn-area">
					<button type="submit" id="saveMissionBtn">決定</button>
					<button type="button" onclick="reloadMission()">再選出</button>
				</div>

			</form>

		</div>
	</div>
	<script src="<%=request.getContextPath()%>/js/mission.js"></script>
</body>
</html>