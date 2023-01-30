// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

var chartArea = {
  labels : [],
  dataSets : [],
  render : function() {
    new Chart(document.getElementById('line-chart'), {
      type: 'line',
      data: {
        labels: chartArea.labels,
        datasets: [{
          label: "재고",
          lineTension: 0.3,
          backgroundColor: "rgba(2,117,216,0.2)",
          borderColor: "rgba(2,117,216,1)",
          pointRadius: 5,
          pointBackgroundColor: "rgba(2,117,216,1)",
          pointBorderColor: "rgba(255,255,255,0.8)",
          pointHoverRadius: 5,
          pointHoverBackgroundColor: "rgba(2,117,216,1)",
          pointHitRadius: 50,
          pointBorderWidth: 2,
          data: chartArea.dataSets,
        }],
      },
      options: {
        responsive : true,
        scales: {
          xAxes: [{
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
              ticks: {
              min: 0,
              stepSize: 1,
            },
            gridLines: {
              color: "rgba(0, 0, 0, .125)",
            }
          }],
        },
        legend: {
          display: false
        }
      }
    });
  },
  showData : function(){
    labels = [];
    dataSets= [];
    $.ajax({
      type : 'GET',
      url : document.location.href + '/getItemHistory',
      contentType: 'application/json',
      //dataType 정의
      dataType: 'json',
      //요청결과가 성공일 경우
      success : function(data) {
        //console.log(data);

        $.each(data, function(index,obj){
           var date = new Date(obj.ts);
           date.setHours(date.getHours()-9);
           var year = date.getFullYear().toString().slice(-2); //년도 뒤에 두자리
           var month = ("0" + (date.getMonth() + 1)).slice(-2); //월 2자리 (01, 02 ... 12)
           var day = ("0" + date.getDate()).slice(-2); //일 2자리 (01, 02 ... 31)
           var returnDate = year+"."+month+"."+day;
          chartArea.dataSets.push(obj.stock);
          chartArea.labels.push(returnDate);
        });
        chartArea.render();
      },
      //요청결과가 실패일 경우
      error : function(xhr, status, error){
        alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);
      }
    });
  }
};

$(document).ready(function(){
    		chartArea.showData();
    	});