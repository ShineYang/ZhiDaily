package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by ShineYang on 2017/4/13.
 */

public interface ISplashView extends MvpView {
    void setSplashImage(String url);
    void onError();
}
