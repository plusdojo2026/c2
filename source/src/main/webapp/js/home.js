console.log("home.js 読み込み成功");

window.addEventListener('DOMContentLoaded', () => {

	console.log("DOM読み込み完了");

	console.log(
		"ボタン数=",
		document.querySelectorAll('.complete-btn').length
	);

	document.querySelectorAll('.complete-btn').forEach(button => {

		button.addEventListener('click', () => {

			console.log("クリックされた");

			const missionName = button.dataset.mission;
			const complete = button.dataset.complete;

			console.log(missionName);
			console.log(complete);

		});

	});

	const ctx = document.getElementById('radarChart');

	console.log("radarData=", radarData);

	new Chart(ctx, {
		type: 'radar',
		data: {
			labels: ['生活', '運動', 'お金', '趣味', '勉強'],
			datasets: [{
				label: '達成数',
				data: radarData,
				fill: true,
				backgroundColor: 'rgba(54, 162, 235, 0.2)',
				borderColor: 'rgb(54, 162, 235)',
				pointBackgroundColor: 'rgb(54, 162, 235)'
			}]
		},
		options: {
			responsive: false,
			scales: {
				r: {
					beginAtZero: true,
					min: 0,
					max: 5,
					ticks: {
						stepSize: 1
					}
				}
			}
		}
	});
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