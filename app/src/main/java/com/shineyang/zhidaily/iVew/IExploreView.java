package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.adapter.item.SectionItem;
import com.shineyang.zhidaily.bean.TopBanners;

import java.util.List;

/**
 * Created by ShineYang on 2017/1/15.
 */

public interface IExploreView extends MvpView {

    void showMainStoryContent(String date, TopBanners banners, List<SectionItem> mainStoryItemListWithHeader);

    void refresh(String date, TopBanners banners, List<SectionItem> mainStoryItemListWithHeader);

    void loadMore(String date, List<SectionItem> moreStoryItemListWithHeader);

    void dismissRefresh();

    void showSuccess();

    void showFailed();

    void showRefreshFailed();

    void showLoadMoreFailed();
}
