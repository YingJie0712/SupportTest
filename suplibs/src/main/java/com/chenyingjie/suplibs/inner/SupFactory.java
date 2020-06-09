package com.chenyingjie.suplibs.inner;

/**
 * create by chenyingjie on 2020/6/9
 * desc
 */
public class SupFactory {

    public SupFactory() {

    }

    public SupRewardAd create() {
        return new SupRewardAdImp();
    }
}
