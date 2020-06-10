package com.chenyingjie.supporttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chenyingjie.suplibs.http.SupOkCallback;
import com.chenyingjie.suplibs.http.SupOkClient;
import com.chenyingjie.suplibs.open.SupLoadRewardAd;
import com.lianzhuoxinxi.lzxx_sdk.open.LzAdSdk;
import com.lianzhuoxinxi.lzxx_sdk.open.ad.LzxxBannerAd;
import com.lianzhuoxinxi.lzxx_sdk.open.admanager.LzBannerAd;
import com.lianzhuoxinxi.lzxx_sdk.open.admanager.LzRewardVideoAd;
import com.lianzhuoxinxi.lzxx_sdk.open.error.AdException;
import com.lianzhuoxinxi.lzxx_sdk.open.listener.BannerAdLoadListener;
import com.lianzhuoxinxi.lzxx_sdk.open.listener.RewardAdListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btnReward, btnBanner;
    private TextView textView;
    private FrameLayout flContainer;

    private String url = "https://raw.github.com/square/okhttp/master/README.md";
    private SupLoadRewardAd rewardAd;
    private LzBannerAd bannerAd;
    private LzRewardVideoAd rewardVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.textView);
        flContainer = findViewById(R.id.flContainer);
        btnReward = findViewById(R.id.btnReward);
        btnBanner = findViewById(R.id.btnBanner);

        rewardAd = new SupLoadRewardAd();
        bannerAd = new LzBannerAd(this);
        rewardVideoAd = new LzRewardVideoAd(this);

        LzAdSdk.getLzxxManager().requestPermissionIfNecessary(this);

        btn1.setOnClickListener(v -> req());
        btn2.setOnClickListener(v -> load());
        btnReward.setOnClickListener(v -> reward());
        btnBanner.setOnClickListener(v -> banner());
    }

    private void req() {
        SupOkClient.getInstance().getAsyc(url, new SupOkCallback() {
            @Override
            public void onError(int code, String message) {
                textView.setText("code: " + code + ", message: " + message);
            }

            @Override
            public void onResult(String response) {
                textView.setText(response);
            }
        });
    }

    private void load() {
        rewardAd.loadAd(this);
    }

    private void reward() {
        rewardVideoAd.loadRewardAd("36", new RewardAdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("MainActivity", "onAdLoaded");
            }

            @Override
            public void onAdShow() {
                Log.e("MainActivity", "onAdShow");
            }

            @Override
            public void onAdClick() {
                Log.e("MainActivity", "onAdClick");
            }

            @Override
            public void onAdClose() {
                Log.e("MainActivity", "onAdClose");
            }

            @Override
            public void onReward() {
                Log.e("MainActivity", "onReward");
            }

            @Override
            public void onVideoStart() {
                Log.e("MainActivity", "onVideoStart");
            }

            @Override
            public void onVideoComplete() {
                Log.e("MainActivity", "onVideoComplete");
            }

            @Override
            public void onAdError(AdException e) {
                Log.e("MainActivity", "onAdError:" + e.getMessage());
            }
        });
    }

    private void banner() {
        bannerAd.loadBannerAd("37", new BannerAdLoadListener() {
            @Override
            public void onAdLoadFailed(AdException e) {
                Log.e("MainActivity", e.getMessage());
            }

            @Override
            public void onAdLoadSuccess(List<LzxxBannerAd> list) {
                if (list != null && list.size() > 0) {
                    LzxxBannerAd lzxxBannerAd = list.get(0);
                    if (lzxxBannerAd != null) {
                        View bannerAdView = lzxxBannerAd.getBannerAdView();
                        if (bannerAdView != null && bannerAdView.getParent() != null) {
                            ViewGroup parent = (ViewGroup) bannerAdView.getParent();
                            parent.removeView(bannerAdView);
                        }
                        lzxxBannerAd.setBannerAdListener(new LzxxBannerAd.BannerAdListener() {
                            @Override
                            public void onClick() {
                                Log.e("MainActivity", "onClick");
                            }

                            @Override
                            public void onAdShow() {
                                Log.e("MainActivity", "onAdShow");
                            }

                            @Override
                            public void onAdRenderSuccess() {
                                Log.e("MainActivity", "onAdRenderSuccess");
                            }

                            @Override
                            public void onAdRenderError(AdException e) {
                                Log.e("MainActivity", "onAdRenderError:" + e.getMessage());
                            }
                        });
                        lzxxBannerAd.render();
                        flContainer.removeAllViews();
                        flContainer.addView(bannerAdView);
                    }
                }
            }
        });
    }
}