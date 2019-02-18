package com.example.mm.weather;

import android.util.Log;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNow;
import interfaces.heweather.com.interfacesmodule.bean.search.Search;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.Hourly;
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.HourlyBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class Weather {

    private CitySearch citySearch;
    private MainActivity mainActivity;

    public String tmp_now = "";
    public String tmp_body_now = "";
    public String weather_code_now = "";
    public String weather_txt_now = "";
    public String humidity_now = "";
    public String rainfall_now = "";  //降水量
    public String visibility_now = "";    //能见度
    public String aqi_now="";
    public String quality_now="";
    public String pm25_now="";
    public List<HourlyBase> hourlyBaseList;
    public List<ForecastBase> forecastBaseList;


    public Weather(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        citySearch = new CitySearch();
        HeConfig.init("HE1809122334451348", "bf6f0b1535fb47e0a2589f54be5bcda8");
        HeConfig.switchToFreeServerNode();
    }

    /**
     * 按小时预报（仅取24小时数据）
     */
    public void getHourInfo()
    {
        HeWeather.getWeatherHourly(mainActivity, citySearch.cityId, new HeWeather.OnResultWeatherHourlyBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e("Weather ErrorHour",throwable.getMessage());
            }

            @Override
            public void onSuccess(List<Hourly> list) {
                Log.d("Weather","list:"+list.size()+"hour:"+list.get(0).getHourly().size());
                hourlyBaseList=list.get(0).getHourly();
            }
        });
    }

    public void getDayInfo()
    {
        HeWeather.getWeatherForecast(mainActivity, citySearch.cityId, new HeWeather.OnResultWeatherForecastBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e("Weather ErrorDay",throwable.getMessage());
            }

            @Override
            public void onSuccess(List<Forecast> list) {
                Log.d("Weather","list:"+list.size()+"day:"+list.get(0).getDaily_forecast().size());
                forecastBaseList=list.get(0).getDaily_forecast();
            }
        });
    }

    public void getAirNow()
    {
        HeWeather.getAirNow(mainActivity,citySearch.cityId,new HeWeather.OnResultAirNowBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e("Weather Error3",throwable.getMessage());
            }

            @Override
            public void onSuccess(List<AirNow> list) {
                Log.d("Weather Request","City is:"+list.get(0).getBasic().getAdmin_area()+list.get(0).getBasic().getLocation());
                aqi_now=list.get(0).getAir_now_city().getAqi();
                quality_now=list.get(0).getAir_now_city().getQlty();
                pm25_now=list.get(0).getAir_now_city().getPm25();
                Log.d("JZM",pm25_now);
            }
        });
    }

    public void getWeatherNow()
    {
        HeWeather.getWeatherNow(mainActivity,citySearch.cityId,new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e("Weather Error2",throwable.getMessage());
            }

            @Override
            public void onSuccess(List<Now> list) {
                Log.d("Weather",""+list.size());
                NowBase nowBase=list.get(0).getNow();
                tmp_now=nowBase.getTmp();
                tmp_body_now=nowBase.getFl();
                weather_code_now=nowBase.getCond_code();
                weather_txt_now=nowBase.getCond_txt();
                humidity_now=nowBase.getHum();
                rainfall_now=nowBase.getPcpn();
                visibility_now=nowBase.getVis();
            }
        });
    }

    public String getCityID()
    {
        HeWeather.getSearch(mainActivity,mainActivity.getLocationListener().city,"world",10, Lang.CHINESE_SIMPLIFIED,citySearch);
        Log.d("WWW",mainActivity.getLocationListener().province+mainActivity.getLocationListener().city);
        Log.d("City ID",citySearch.cityId+"***********");
        return citySearch.cityId;
    }

    private class CitySearch implements HeWeather.OnResultSearchBeansListener
    {
        String cityId="";

        @Override
        public void onError(Throwable throwable) {
            Log.e("Weather Error1",throwable.getMessage());
        }

        @Override
        public void onSuccess(Search search) {
            int i=0;

            cityId=search.getBasic().get(0).getCid();
            Log.d("CityIDD",search.getBasic().get(0).getCid());
        }
    }
}
