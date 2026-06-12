let selectedMissions = [];

// ミッション取得
function loadMission(theme) {

    window.currentTheme = theme; // ★これ追加（超重要）

    fetch("MissionServlet?theme=" + encodeURIComponent(theme))
        .then(response => response.json())
        .then(data => {

            selectedMissions = data;

            const missionList = document.getElementById("missionList");
            missionList.innerHTML = "";

            data.forEach(mission => {
                missionList.innerHTML += "<p>" + mission + "</p>";
            });

            document.getElementById("missionPopup").style.display = "block";
        });
}

// ポップアップを閉じる（×ボタン用）
function closePopup() {
    document.getElementById("missionPopup").style.display = "none";
}

// 背景クリックで閉じる
window.addEventListener("click", function (event) {

    const popup = document.getElementById("missionPopup");

    if (event.target === popup) {
        closePopup();
    }
});

// フォーム送信時
document.addEventListener("DOMContentLoaded", function () {

    document.getElementById("missionForm").addEventListener("submit", function () {

        document.getElementById("missionData").value =
            selectedMissions.join(",");
    });
});

function reloadMission() {

    // 今のポップアップを閉じる
    document.getElementById("missionPopup").style.display = "none";

    // 直前のテーマを再利用する必要がある
    if (!window.currentTheme) {
        console.log("テーマがありません");
        return;
    }

    loadMission(window.currentTheme);
}