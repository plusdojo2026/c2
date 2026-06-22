console.log("home.js 読み込み成功");

window.addEventListener('DOMContentLoaded', () => {

	console.log(
		"ボタン数=",
		document.querySelectorAll('.complete-btn').length
	);

	document.querySelectorAll('.complete-btn').forEach(button => {

		button.addEventListener('click', () => {

			const missionName = button.dataset.mission;
			const complete = button.dataset.complete;

			console.log(missionName);
			console.log(complete);

		});

	});

	const ctx = document.getElementById('radarChart');

	const maxValue = Math.max(...radarData);

	new Chart(ctx, {
		type: 'radar',
		data: {
			labels: [
				`生活 (${radarData[0]})`,
				`運動 (${radarData[1]})`,
				`お金 (${radarData[2]})`,
				`趣味 (${radarData[3]})`,
				`勉強 (${radarData[4]})`
			],
			datasets: [{
				label: '達成数',
				data: radarData,
				fill: true,
				backgroundColor: 'rgba(255, 182, 193, 0.4)',
				borderColor: '#ff8fb1'
			}]
		},
		options: {
			responsive: false,
			scales: {
				r: {
					beginAtZero: true,
					max: Math.ceil(maxValue * 1.5),

					ticks: {
						display: false
					},

					grid: {
						lineWidth: 1.5
					}
				}
			}
		}
	});

	document.querySelectorAll('.complete-btn').forEach(button => {

		button.addEventListener('click', () => {

			const missionName = button.dataset.mission;
			const complete = button.dataset.complete;

			fetch('CompleteServlet', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body:
					'missionName=' +
					encodeURIComponent(missionName) +
					'&complete=' +
					complete
			})
				.then(response => response.text())
				.then(result => {

					if (result === 'success') {

						const row = button.closest('.mission-row');

						row.querySelectorAll('.complete-btn')
							.forEach(btn =>
								btn.classList.remove('active')
							);

						button.classList.add('active');
					}
				});
		});

	});
});