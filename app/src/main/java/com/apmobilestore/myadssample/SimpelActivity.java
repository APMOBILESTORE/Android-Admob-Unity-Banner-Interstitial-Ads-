package com.apmobilestore.myadssample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


public class SimpelActivity extends AppCompatActivity {
    private AdView adView;
    private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpel);

        //comman function for banner and inter ads
        AdRequest adRequest = new AdRequest.Builder().build();
        // end comman function for banner and inter ads

        // start banner ads function
        adView = findViewById(R.id.adView);
        adView.loadAd(adRequest);
        //end banner ads function

       //  start inter ads function
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                SimpelActivity.this.interstitialAd = interstitialAd;
                // show sds function
                interstitialAd.show(SimpelActivity.this);
            }
        });
        // end inter ads function
    }
}