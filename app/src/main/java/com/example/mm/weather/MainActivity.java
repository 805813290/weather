package com.example.mm.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class MainActivity extends Activity {

    private LocationClient mLocationClient=null;
    private LocationListener locationListener;
    private Weather weather;
    private WebView webView;

    private static final int BD_PERMISSION_STATE =100;
    private final String[] BD_PERMISSIONS=new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };

    private boolean[] permissions_granted=new boolean[BD_PERMISSIONS.length];

    private final int REFEASH_RATE=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*配置html显示及JS链接*/
        webView=findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,String description, String failingUrl)
            {
                Log.e("Load Error","Code is:"+errorCode+" "+description);
            }
        });
        webView.addJavascriptInterface(new JSInterface(this),"Weather");
        webView.loadUrl("file:///android_asset/index.html");
        Log.e("Over","Over");

        /*获取所有权限*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int need_sum = 0;
            for (int i = 0; i < BD_PERMISSIONS.length; ++i) {
                if (checkSelfPermission(BD_PERMISSIONS[i]) == PackageManager.PERMISSION_DENIED) {
                    ++need_sum;
                    permissions_granted[i] = false;
                } else permissions_granted[i] = true;
            }
            if(need_sum!=0)
            {
                String[] request_permissions = new String[need_sum];
                Log.d("Requset permission", "Sum:" + need_sum);
                for (int i = 0, j = 0; i < BD_PERMISSIONS.length; ++i) {
                    if (!permissions_granted[i]) {
                        request_permissions[j] = BD_PERMISSIONS[i];
                        Log.d("No permission", request_permissions[j]);
                        ++j;
                    }
                }
                requestPermissions(request_permissions, BD_PERMISSION_STATE);
            }
        }

        /*配置百度api*/
        mLocationClient=new LocationClient(getApplicationContext());
        locationListener=new LocationListener(this,mLocationClient);
        mLocationClient.registerLocationListener(locationListener);
        LocationClientOption option=new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setScanSpan(REFEASH_RATE);
        option.setIgnoreKillProcess(false);

        mLocationClient.setLocOption(option);

        weather=new Weather(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mLocationClient.start();
        mLocationClient.requestLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        switch(requestCode) {
            case BD_PERMISSION_STATE:
                for(int i=0;i<permissions.length;++i)
                {
                    if(grantResults[i]==PackageManager.PERMISSION_GRANTED)
                    {
                        for(int j=0;j<BD_PERMISSIONS.length;j++)
                        {
                            if(BD_PERMISSIONS[j].equals(permissions[i]))
                            {
                                permissions_granted[j]=true;
                            }
                        }
                    }
                    else
                    {
                        Log.e("Permission failed", permissions[i]);
                        Toast.makeText(this,"未获取到"+permissions[i]+"权限",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                Log.e("Unkonwn request","requestCode is:"+requestCode);
                break;
        }
        mLocationClient.restart();
    }

    public LocationListener getLocationListener() {
        return locationListener;
    }

    public Weather getWeather()
    {
        return weather;
    }

    public WebView getWebView() {
        return webView;
    }

    public LocationClientOption getLocClientOpt() {
        return mLocationClient.getLocOption();
    }
}
