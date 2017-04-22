package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.bean.Column;

/**
 * Created by ShineYang on 2017/4/18.
 */

public interface IColumnContentListView extends MvpView {
    void showContentList(Column columnContent);
    void onError();
    void onLoadMore();
}
