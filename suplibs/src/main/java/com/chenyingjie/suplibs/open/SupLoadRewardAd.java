package com.chenyingjie.suplibs.open;

import android.content.Context;

import com.chenyingjie.suplibs.inner.SupFactory;
import com.chenyingjie.suplibs.inner.SupRewardAd;

/**
 * create by chenyingjie on 2020/6/9
 * desc
 */
public class SupLoadRewardAd {

    private SupFactory supFactory;

    public SupLoadRewardAd() {
        supFactory = new SupFactory();
    }

    public void loadAd(Context context) {
        if (supFactory != null) {
            SupRewardAd supRewardAd = supFactory.create();
            supRewardAd.loadSup(context);
        }
    }
}
