package com.shineyang.zhidaily.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.api.ApiQueryBuilder;
import com.shineyang.zhidaily.bean.HotColumn;
import com.shineyang.zhidaily.iVew.IHotColumnView;

import rx.Subscriber;

/**
 * Created by ShineYang on 2017/4/20.
 */

public class HotColumnPresenter extends MvpBasePresenter<IHotColumnView> {
    public void getLatestList(String id) {
        final IHotColumnView hotColumnView = getView();
        if (hotColumnView != null) {
            Subscriber<HotColumn> subscriber = new Subscriber<HotColumn>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    hotColumnView.onError();
                }

                @Override
                public void onNext(HotColumn hotColumn) {
                    hotColumnView.showContentList(hotColumn);
                }
            };
            ApiQueryBuilder.getInstance().getHotColumnList(id, subscriber);
        }
    }

    public void getPastList(String id, String timeStamp) {
        final IHotColumnView hotColumnView = getView();
        if (hotColumnView != null) {
            Subscriber<HotColumn> subscriber = new Subscriber<HotColumn>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    hotColumnView.onError();
                }

                @Override
                public void onNext(HotColumn hotColumn) {
                    hotColumnView.onLoadMore(hotColumn);
                }
            };
            ApiQueryBuilder.getInstance().getPastHotColumnList(id, timeStamp, subscriber);
        }
    }
}
