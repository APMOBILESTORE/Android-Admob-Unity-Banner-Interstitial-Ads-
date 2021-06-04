# Android  Admob Unity  Banner Interstitial Ads Sample
My Ads Sample<br>
Admob and UnityAds<br>
<br>

Project Configuration<br>
Android Studio Virsion= 4.2</br>
Gradle: Virsion= 4.2.1<br>
CompileSdk Version= 30<br>
minSdkVersion= 19<br>
targetSdkVersion= 30<br>
play service ads virsion = implementation 'com.google.android.gms:play-services-ads:20.1.0'<br>

<br>
If you start ads with UnityAds Please falow below steps<br>
Not For UnityAds:<br>
1. Add this file <a heref="https://github.com/APMOBILESTORE/MyAdsSample/blob/master/unity-ads.aar"><b>unity-ads.aar</a></b> in app/libs <br>.
2. Edit or ad this line  <b>implementation fileTree(dir: 'libs', include: ['*.jar','*.aar'])</b> in  app/build.gradle  implementation section.
