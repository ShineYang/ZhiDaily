package com.shineyang.zhidaily.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.api.ApiQueryBuilder;
import com.shineyang.zhidaily.bean.SplashInfo;
import com.shineyang.zhidaily.iVew.ISplashView;

import rx.Subscriber;

/**
 * Created by ShineYang on 2017/4/13.
 */

public class SplashPresenter extends MvpBasePresenter<ISplashView> {
    public void getSplashImageUrl() {
        final ISplashView splashView = getView();
        if (splashView != null) {
            Subscriber<SplashInfo> subscriber = new Subscriber<SplashInfo>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    splashView.onError();
                }

                @Override
                public void onNext(SplashInfo splashInfo) {
                    splashView.setSplashImage(splashInfo.getSplashViewInfoList().get(0).getUrl());
                }
            };

            ApiQueryBuilder.getInstance().getSplashImage(1080, 1920, subscriber);
        }
    }
}
