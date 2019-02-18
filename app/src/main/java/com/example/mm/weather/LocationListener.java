package com.example.mm.weather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;

public class LocationListener extends BDAbstractLocationListener {

    private MainActivity mainActivity;
    private LocationClient locationClient;
    public String addr="";    //获取详细地址信息
    public String country="";    //获取国家
    public String province="";    //获取省份
    public String city="";    //获取城市
    public String district="";    //获取区县
    public String street="";    //获取街道信息
    public String adcode="";
    /**
     * 经度坐标
     */
    public double longitude=0;
    /**
     * 纬度坐标
     */
    public double latitude=0;

    private boolean isUpdated=false;

    public LocationListener(MainActivity mainActivity,LocationClient locationClient)
    {
        this.mainActivity=mainActivity;
        this.locationClient=locationClient;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        longitude=bdLocation.getLongitude();
        latitude=bdLocation.getLatitude();
        adcode=bdLocation.getAdCode();
        addr=bdLocation.getAddrStr();
        country=bdLocation.getCountry();
        province=bdLocation.getProvince();
        city=bdLocation.getCity();
        district=bdLocation.getDistrict();
        street=bdLocation.getStreet();
        if(province==null || city == null)
        {
            Log.d("Location error",bdLocation.getLocationDescribe()+" LocType is:"+bdLocation.getLocType());
            return;
        }

        /*先获取城市ID以查询*/
        mainActivity.getWeather().getCityID();
        mainActivity.getWeather().getAirNow();
        mainActivity.getWeather().getWeatherNow();
        mainActivity.getWeather().getHourInfo();
        mainActivity.getWeather().getDayInfo();

        if(!isUpdated&&!mainActivity.getWeather().weather_txt_now.equals(""))
        {
            mainActivity.getLocClientOpt().setScanSpan(10000);
            mainActivity.getWebView().reload();
            isUpdated=true;
        }
    }

    @Override
    public void onLocDiagnosticMessage(int var1, int var2, String var3) {
        Log.w("Service log",var3+" locType:"+var1+" diagnosticType:"+var2);
        if(var1==167)
        {
            Toast.makeText(mainActivity,"请检查定位开关或定位权限",Toast.LENGTH_LONG).show();
            locationClient.restart();
        }
        else if(var1==62)
        {
            Toast.makeText(mainActivity,"请检查相应权限是否开启",Toast.LENGTH_LONG).show();
            locationClient.restart();
        }
        else if(var1==162)
        {
            Toast.makeText(mainActivity,"抱歉,此CPU无法适配",Toast.LENGTH_LONG).show();
        }
    }
}
