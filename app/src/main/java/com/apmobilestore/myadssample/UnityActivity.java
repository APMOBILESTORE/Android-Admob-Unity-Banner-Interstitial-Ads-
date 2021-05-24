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

public class UnityActivity extends AppCompatActivity {
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
        UnityAds.initialize(UnityActivity.this,GameID,testMode);
        IUnityAdsListener unityAdsListener = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {
                Toast.makeText(UnityActivity.this, "Interstitial Ad ready" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onUnityAdsStart(String s) {
                Toast.makeText(UnityActivity.this, "Interstititl is playing", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
                Toast.makeText(UnityActivity.this, "Interstitial is Finished" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
                Toast.makeText(UnityActivity.this, unityAdsError.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        UnityAds.setListener(unityAdsListener);

        if (UnityAds.isInitialized()){
            UnityAds.load(interstitialAdPlacement);
            UnityBanners.loadBanner(UnityActivity.this,bannerAdPlacement);

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
                            UnityBanners.loadBanner(UnityActivity.this,bannerAdPlacement);
                        }
                    },5000);
                }
            },5000);
        }
        // benner ads
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
/*
        Button bannerBtn = findViewById(R.id.show_banner);
        Button interstitalBtn = findViewById(R.id.show_interstitial);

        interstitalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnityAds.load(interstitialAdPlacement);
                DisplayInterstitialAd();

            }
        });

        bannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnityBanners.loadBanner(UnityActivity.this,bannerAdPlacement);
            }
        });
*/
    }

    private  void DisplayInterstitialAd (){
        if (UnityAds.isReady(interstitialAdPlacement)){
            UnityAds.show(UnityActivity.this,interstitialAdPlacement);
        }
    }
}