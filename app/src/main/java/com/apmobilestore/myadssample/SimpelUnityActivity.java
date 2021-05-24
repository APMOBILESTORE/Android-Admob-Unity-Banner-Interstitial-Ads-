package com.apmobilestore.myadssample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class SimpelUnityActivity extends AppCompatActivity {
    //ads
    private String GameID = "4137191";
    private boolean testMode = true;
    private String interstitialAdPlacement = "Interstitial_Android";
    private String bannerAdPlacement = "Banner_Android";
    //stop ads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity);
// start ads function
        UnityAds.initialize(SimpelUnityActivity.this,GameID,testMode);
        if (UnityAds.isInitialized()){
            UnityAds.load(interstitialAdPlacement);
            UnityBanners.loadBanner(SimpelUnityActivity.this,bannerAdPlacement);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DisplayInterstitialAd();
                }
            },5000);
        }else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    UnityAds.load(interstitialAdPlacement);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DisplayInterstitialAd();
                            UnityBanners.loadBanner(SimpelUnityActivity.this,bannerAdPlacement);
                        }
                    },5000);
                }
            },5000);
        }
        // benner ads function
        IUnityBannerListener iUnityBannerListener = new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                ((ViewGroup) findViewById(R.id.banner_ads_view)).removeView(view);
                ((ViewGroup) findViewById(R.id.banner_ads_view)).addView(view);
            }
            @Override
            public void onUnityBannerUnloaded(String s) {
            }
            @Override
            public void onUnityBannerShow(String s) {
            }
            @Override
            public void onUnityBannerClick(String s) {
            }
            @Override
            public void onUnityBannerHide(String s) {
            }
            @Override
            public void onUnityBannerError(String s) {
            }
        };
        UnityBanners.setBannerListener(iUnityBannerListener);
        //end banner ads function
    }

    private  void DisplayInterstitialAd (){
        if (UnityAds.isReady(interstitialAdPlacement)){
            UnityAds.show(SimpelUnityActivity.this,interstitialAdPlacement);
        }
    }
}