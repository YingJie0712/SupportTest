package com.chenyingjie.supporttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.chenyingjie.suplibs.http.SupOkCallback;
import com.chenyingjie.suplibs.http.SupOkClient;
import com.chenyingjie.suplibs.open.SupLoadRewardAd;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2;
    private TextView textView;

    private String url = "https://raw.github.com/square/okhttp/master/README.md";
    private SupLoadRewardAd rewardAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.textView);

        rewardAd = new SupLoadRewardAd();

        btn1.setOnClickListener(v -> req());
        btn2.setOnClickListener(v -> load());
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
}