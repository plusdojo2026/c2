window.addEventListener('DOMContentLoaded', () => {
  const ctx = document.getElementById('radarChart');

  new Chart(ctx, {
    type: 'radar',
    data: {
      labels: ['生活', '運動', 'お金', '趣味', '勉強'],
      datasets: [{
        label: 'サンプルデータ',
        data: [3, 4, 2, 5, 4],
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
          ticks: { stepSize: 1 }
        }
      }
    }
  });
});
