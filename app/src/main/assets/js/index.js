// header-head_title
// get city
document.getElementById("city").innerHTML = Weather.getAddress();
// weather-w_left
var w_lf = document.getElementById("w_left");
w_lf.style.backgroundImage='url(image/'+ Weather.getWeatherCode() + '.png)';
//console.log('url(image/'+ Weather.getWeatherCode() + '.png);');
// weather-w_right
// get temper
document.getElementById("w_temper").innerHTML = Weather.getTmpNow() + "℃";
// get w_txt
document.getElementById("w_txt").innerHTML = Weather.getWeatherTxt();
// get pm
document.getElementById("pm").innerHTML = "PM2.5 " + Weather.getPM25Now();
// get air
document.getElementById("air").innerHTML = "空气质量" + Weather.getQualityNow()+ Weather.getAQINow();
// get date
var show_date = new Date();
var show_date_p = document.getElementById("date");
show_date_p.innerHTML = show_date.getFullYear()+"/"+ (show_date.getMonth()+1) +"/"+show_date.getDate();
// get weekday
var week = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
var show_day_p = document.getElementById("day");
show_day_p.innerHTML = week[show_date.getDay()];

// update
function update() {
	//Weather.update();
	document.location.reload();
	console.log('reload');
}

// tab
function show(n){
//		var curr = document.querySelector("div[style]");
//		curr.removeAttribute("style");
//        curr.style.zIndex="0"
        document.getElementById("content1").removeAttribute("style");
        document.getElementById("content2").removeAttribute("style");
		document.getElementById("content"+n).style.zIndex="1";
//		document.getElementById("w_left").style.backgroundImage='url(image/'+ Weather.getWeatherCode() + '.png)';
//        console.log('url(image/'+ Weather.getWeatherCode() + '.png)');
//		console.log('show');
}