package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.bean.HotColumn;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/20.
 */

public interface IHotColumnView extends MvpView {
    void showContentList(HotColumn hotColumn);
    void onLoadMore(HotColumn hotColumn);
    void onError();
    void onLoadMoreError();
}
