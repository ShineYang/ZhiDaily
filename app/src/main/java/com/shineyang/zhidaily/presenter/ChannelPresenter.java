package com.shineyang.zhidaily.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.api.ApiQueryBuilder;
import com.shineyang.zhidaily.bean.PastColumns;
import com.shineyang.zhidaily.iVew.IChannelView;

import rx.Subscriber;

/**
 * Created by ShineYang on 2017/4/11.
 */

public class ChannelPresenter extends MvpBasePresenter<IChannelView> {
    public void getPastColumnList() {
        final IChannelView channelView = getView();
        if (channelView != null) {
            Subscriber<PastColumns> subscriber = new Subscriber<PastColumns>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    channelView.onError();
                }

                @Override
                public void onNext(PastColumns pastColumn) {
                    channelView.showPastList(pastColumn.getOthers());
                }
            };

            ApiQueryBuilder.getInstance().getPastColumn(subscriber);
        }
    }
}
