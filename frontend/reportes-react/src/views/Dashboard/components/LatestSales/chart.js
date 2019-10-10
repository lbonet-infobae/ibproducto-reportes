import palette from 'theme/palette';

export const data = {
         labels: [
           '1 Aug',
           '2 Aug',
           '3 Aug',
           '4 Aug',
           '5 Aug',
           '6 Aug',
           '7 Aug',
           '8 Aug',
           '9 Aug',
           '10 Aug',
           '11 Aug',
           '12 Aug',
           '13 Aug',
           '14 Aug',
           '15 Aug',
           '16 Aug',
           '17 Aug',
           '18 Aug',
           '19 Aug',
           '20 Aug',
           '21 Aug'
         ],
         datasets: [
           {
             label: 'This year',
             backgroundColor: palette.primary.main,
             data: [
               18,
               5,
               19,
               27,
               29,
               19,
               20,
               18,
               5,
               19,
               27,
               29,
               19,
               20,
               18,
               5,
               19,
               27,
               29,
               19,
               20
             ]
           },
           {
             label: 'Last year',
             backgroundColor: palette.neutral,
             data: [
               11,
               20,
               12,
               29,
               30,
               25,
               13,
               11,
               20,
               12,
               29,
               30,
               25,
               13,
               18,
               5,
               19,
               27,
               29,
               19,
               20
             ]
           }
         ]
       };

export const options = {
  responsive: true,
  maintainAspectRatio: false,
  animation: false,
  legend: { display: false },
  cornerRadius: 20,
  tooltips: {
    enabled: true,
    mode: 'index',
    intersect: false,
    borderWidth: 1,
    borderColor: palette.divider,
    backgroundColor: palette.white,
    titleFontColor: palette.text.primary,
    bodyFontColor: palette.text.secondary,
    footerFontColor: palette.text.secondary
  },
  layout: { padding: 0 },
  scales: {
    xAxes: [
      {
        barThickness: 12,
        maxBarThickness: 10,
        barPercentage: 0.5,
        categoryPercentage: 0.5,
        ticks: {
          fontColor: palette.text.secondary
        },
        gridLines: {
          display: false,
          drawBorder: false
        }
      }
    ],
    yAxes: [
      {
        ticks: {
          fontColor: palette.text.secondary,
          beginAtZero: true,
          min: 0
        },
        gridLines: {
          borderDash: [2],
          borderDashOffset: [2],
          color: palette.divider,
          drawBorder: false,
          zeroLineBorderDash: [2],
          zeroLineBorderDashOffset: [2],
          zeroLineColor: palette.divider
        }
      }
    ]
  }
};
