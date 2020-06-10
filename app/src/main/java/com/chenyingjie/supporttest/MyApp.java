package com.chenyingjie.supporttest;

import android.app.Application;

import com.lianzhuoxinxi.lzxx_sdk.open.LzAdSdk;

/**
 * create by chenyingjie on 2020/6/10
 * desc
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LzAdSdk.init(this, "088730932476c9c97acd9bf239bdad4e");
    }
}
