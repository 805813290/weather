var arr_tmp_max = [Weather.getDay1TmpMax(), Weather.getDay2TmpMax(), Weather.getDay3TmpMax(), Weather.getDay4TmpMax(), Weather.getDay5TmpMax(), Weather.getDay6TmpMax(), Weather.getDay7TmpMax()];
var arr_tmp_min = [Weather.getDay1TmpMin(), Weather.getDay2TmpMin(), Weather.getDay3TmpMin(), Weather.getDay4TmpMin(), Weather.getDay5TmpMin(), Weather.getDay6TmpMin(), Weather.getDay7TmpMin()];
var arr_hum = [Weather.getDay1Hum(), Weather.getDay2Hum(), Weather.getDay3Hum(), Weather.getDay4Hum(), Weather.getDay5Hum(), Weather.getDay6Hum(), Weather.getDay7Hum()];
var arr_date = [Weather.getDay1Date(), Weather.getDay2Date(), Weather.getDay3Date(), Weather.getDay4Date(), Weather.getDay5Date(), Weather.getDay6Date(), Weather.getDay7Date()];
var arr=[7,10,15,10,7,5,6];
var date=[1,2,3,4,5,6,7];
var chart = new Chartist.Line('#chart1', {
	labels: arr_date,
	series: [arr_tmp_max,arr_tmp_min]
}, {
	low: 0,
	showPoint: true,
	fullWidth: true
});
var chart = new Chartist.Line('#chart2', {
	labels: arr_date,
	series: [arr_hum]
}, {
	low: 0,
	showPoint: true,
	fullWidth: true
});
chart.on('draw', function(data) {
if(data.type === 'line' || data.type === 'area') {
	data.element.animate({
	  d: {
	    begin: 2000 * data.index,
	    dur: 2000,
	    from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
	    to: data.path.clone().stringify(),
	    easing: Chartist.Svg.Easing.easeOutQuint
  }
});
}
});
//console.log('chart');