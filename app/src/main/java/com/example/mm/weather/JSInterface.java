package com.example.mm.weather;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JSInterface {
    private MainActivity mainActivity;

    public JSInterface(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
    }

    @JavascriptInterface
    /**
     * 返回城市详细地址
     */
    public String getAddress() {
        return mainActivity.getLocationListener().province+mainActivity.getLocationListener().city;
    }

    @JavascriptInterface
    /**
     * 返回天气编码
     */
    public String getWeatherCode(){
        return mainActivity.getWeather().weather_code_now;
    }

    @JavascriptInterface
    /**
     * 返回天气名称
     */
    public String getWeatherTxt(){
        return mainActivity.getWeather().weather_txt_now;
    }

    @JavascriptInterface
    /**
     * 返回湿度
     */
    public String getHumidityNow(){
        return mainActivity.getWeather().humidity_now;
    }

    @JavascriptInterface
    /**
     * 返回当前温度
     */
    public String getTmpNow(){
        return mainActivity.getWeather().tmp_now;
    }

    @JavascriptInterface
    /**
     * 返回降雨量
     */
    public String getRainfallNow() {
        return mainActivity.getWeather().rainfall_now;
    }

    @JavascriptInterface
    /**
     * 返回AQI（空气质量指数
     */
    public String getAQINow() {
        return mainActivity.getWeather().aqi_now;
    }

    @JavascriptInterface
    /**
     * 返回pm2.5
     */
    public String getPM25Now() {
        return mainActivity.getWeather().pm25_now;
    }

    @JavascriptInterface
    /**
     * 空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染
     */
    public String getQualityNow() {
        return mainActivity.getWeather().quality_now;
    }

    @JavascriptInterface
    public int getDay1TmpMax() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(0).getTmp_max());
    }

    @JavascriptInterface
    public int getDay2TmpMax() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(1).getTmp_max());
    }

    @JavascriptInterface
    public int getDay3TmpMax() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(2).getTmp_max());
    }

    @JavascriptInterface
    public int getDay4TmpMax() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(3).getTmp_max());
    }

    @JavascriptInterface
    public int getDay5TmpMax() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(4).getTmp_max());
    }

    @JavascriptInterface
    public int getDay6TmpMax() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(5).getTmp_max());
    }

    @JavascriptInterface
    public int getDay7TmpMax() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(6).getTmp_max());
    }

    @JavascriptInterface
    public String getDay1Date() {
        String s=mainActivity.getWeather().forecastBaseList.get(0).getDate();
        return s.substring(5,s.length());
    }

    @JavascriptInterface
    public String getDay2Date() {
        String s=mainActivity.getWeather().forecastBaseList.get(1).getDate();
        return s.substring(5,s.length());
    }

    @JavascriptInterface
    public String getDay3Date() {
        String s=mainActivity.getWeather().forecastBaseList.get(2).getDate();
        return s.substring(5,s.length());
    }

    @JavascriptInterface
    public String getDay4Date() {
        String s=mainActivity.getWeather().forecastBaseList.get(3).getDate();
        return s.substring(5,s.length());
    }

    @JavascriptInterface
    public String getDay5Date() {
        String s=mainActivity.getWeather().forecastBaseList.get(4).getDate();
        return s.substring(5,s.length());
    }

    @JavascriptInterface
    public String getDay6Date() {
        String s=mainActivity.getWeather().forecastBaseList.get(5).getDate();
        return s.substring(5,s.length());
    }

    @JavascriptInterface
    public String getDay7Date() {
        String s=mainActivity.getWeather().forecastBaseList.get(6).getDate();
        return s.substring(5,s.length());
    }

    @JavascriptInterface
    public int getDay1TmpMin() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(0).getTmp_min());
    }

    @JavascriptInterface
    public int getDay2TmpMin() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(1).getTmp_min());
    }

    @JavascriptInterface
    public int getDay3TmpMin() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(2).getTmp_min());
    }

    @JavascriptInterface
    public int getDay4TmpMin() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(3).getTmp_min());
    }

    @JavascriptInterface
    public int getDay5TmpMin() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(4).getTmp_min());
    }

    @JavascriptInterface
    public int getDay6TmpMin() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(5).getTmp_min());
    }

    @JavascriptInterface
    public int getDay7TmpMin() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(6).getTmp_min());
    }

    @JavascriptInterface
    public int getDay1Hum() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(0).getHum());
    }

    @JavascriptInterface
    public int getDay2Hum() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(1).getHum());
    }

    @JavascriptInterface
    public int getDay3Hum() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(2).getHum());
    }

    @JavascriptInterface
    public int getDay4Hum() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(3).getHum());
    }

    @JavascriptInterface
    public int getDay5Hum() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(4).getHum());
    }

    @JavascriptInterface
    public int getDay6Hum() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(5).getHum());
    }

    @JavascriptInterface
    public int getDay7Hum() {
        return Integer.parseInt(mainActivity.getWeather().forecastBaseList.get(6).getHum());
    }
}
