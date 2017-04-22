package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.bean.PastColumns;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/11.
 */

public interface IChannelView extends MvpView {
    void showPastList(List<PastColumns.PastColumnContent> pastColumnContentList);

    void onError();
}
