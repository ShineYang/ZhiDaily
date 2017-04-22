package com.shineyang.zhidaily.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.api.ApiQueryBuilder;
import com.shineyang.zhidaily.bean.Column;
import com.shineyang.zhidaily.iVew.IColumnContentListView;

import rx.Subscriber;

/**
 * Created by ShineYang on 2017/4/18.
 */

public class ColumnContentListPresenter extends MvpBasePresenter<IColumnContentListView> {
    public void getCloumnContent(String id) {
        final IColumnContentListView columnContentListView = getView();
        if (columnContentListView != null) {
            Subscriber<Column> subscriber = new Subscriber<Column>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    columnContentListView.onError();
                }

                @Override
                public void onNext(Column column) {
                    columnContentListView.showContentList(column);
                }
            };

            ApiQueryBuilder.getInstance().getPastColumnContentList(id, subscriber);
        }
    }
}
