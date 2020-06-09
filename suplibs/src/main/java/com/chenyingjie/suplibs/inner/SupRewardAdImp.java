package com.chenyingjie.suplibs.inner;

import android.content.Context;
import android.content.Intent;

import com.chenyingjie.suplibs.ui.SRewardVideoActivity;

/**
 * create by chenyingjie on 2020/6/9
 * desc
 */
public class SupRewardAdImp implements SupRewardAd {
    @Override
    public void loadSup(Context context) {
        if (context != null) context.startActivity(new Intent(context, SRewardVideoActivity.class));
    }
}
