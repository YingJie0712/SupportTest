package com.chenyingjie.suplibs.http;

/**
 * create by chenyingjie on 2020/6/8
 * desc
 */
public interface SupOkCallback {

    void onError(int code, String message);
    void onResult(String response);
}
