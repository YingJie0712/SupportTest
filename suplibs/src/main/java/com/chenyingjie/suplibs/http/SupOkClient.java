package com.chenyingjie.suplibs.http;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * create by chenyingjie on 2020/6/8
 * desc
 */
public class SupOkClient {

    private static SupOkClient instance;

    private OkHttpClient okHttpClient;
    private Handler mHandler;

    private SupOkClient() {
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static SupOkClient getInstance() {
        if (instance == null) {
            synchronized (SupOkClient.class) {
                if (instance == null) {
                    instance = new SupOkClient();
                }
            }
        }
        return instance;
    }

    public void getAsyc(String url, SupOkCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mHandler.post(() -> {
                if (callback != null) callback.onError(0, e.getMessage());
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                mHandler.post(() -> {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String body = responseBody.toString();
                        if (callback != null) callback.onResult(body);
                    } else {
                        if (callback != null) callback.onError(response.code(), response.message());
                    }
                });
            }
        });
    }
}
