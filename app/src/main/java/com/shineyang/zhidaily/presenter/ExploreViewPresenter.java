package com.shineyang.zhidaily.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.adapter.item.MainStoryItem;
import com.shineyang.zhidaily.adapter.item.SectionItem;
import com.shineyang.zhidaily.api.ApiQueryBuilder;
import com.shineyang.zhidaily.bean.BannerStory;
import com.shineyang.zhidaily.bean.DailyStories;
import com.shineyang.zhidaily.bean.Story;
import com.shineyang.zhidaily.bean.TopBanners;
import com.shineyang.zhidaily.iVew.IExploreView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by ShineYang on 2017/1/15.
 */

public class ExploreViewPresenter extends MvpBasePresenter<IExploreView> {

    public void fetchData(final Boolean isRefreshing) {
        final IExploreView exploreView = getView();

        if (exploreView != null) {
            Subscriber<DailyStories> subscriber = new Subscriber<DailyStories>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    if (isRefreshing) {
                        exploreView.showRefreshFailed();
                        exploreView.dismissRefresh();
                    } else
                        exploreView.showFailed();
                }

                @Override
                public void onNext(DailyStories dailyStories) {
                    String date = dailyStories.getDate();
                    List<BannerStory> bannerStories;
                    List<Story> storyList;

                    List<String> imageUrls = new ArrayList<>();
                    List<String> titles = new ArrayList<>();
                    List<String> idList = new ArrayList<>();
                    List<SectionItem> sectionItems = new ArrayList<>();
                    TopBanners banners = new TopBanners();

                    //日期header section
                    sectionItems.add(new SectionItem(true, date));

                    bannerStories = dailyStories.getBannerStoryList();
                    storyList = dailyStories.getStoryList();

                    for (BannerStory bannerStory : bannerStories) {
                        imageUrls.add(bannerStory.getImage());
                        titles.add(bannerStory.getTitle());
                        idList.add(bannerStory.getId());
                    }
                    banners.setImages(imageUrls);
                    banners.setTitles(titles);
                    banners.setIdList(idList);

                    for (Story story : storyList) {
                        MainStoryItem item = new MainStoryItem();
                        item.setTitle(story.getTitle());
                        item.setImageResource(story.getImages()[0]);
                        item.setId(story.getId());
                        sectionItems.add(new SectionItem(item));
                    }
                    if (isRefreshing) {
                        exploreView.refresh(date, banners, sectionItems);
                        exploreView.dismissRefresh();
                    } else
                        exploreView.showMainStoryContent(date, banners, sectionItems);

                    exploreView.showSuccess();
                }
            };
            ApiQueryBuilder.getInstance().getLatestStories(subscriber);
        }
    }

    public void fetchBeforeData(String date) {
        final IExploreView exploreView = getView();
        if (exploreView != null) {
            Subscriber<DailyStories> subscriber = new Subscriber<DailyStories>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    exploreView.showLoadMoreFailed();
                }

                @Override
                public void onNext(DailyStories dailyStories) {
                    String date = dailyStories.getDate();
                    List<Story> storyList;
                    storyList = dailyStories.getStoryList();

                    //日期header
                    List<SectionItem> sectionItems = new ArrayList<>();
                    sectionItems.add(new SectionItem(true, date));

                    for (Story story : storyList) {
                        MainStoryItem item = new MainStoryItem();
                        item.setTitle(story.getTitle());
                        item.setImageResource(story.getImages()[0]);
                        item.setId(story.getId());
                        sectionItems.add(new SectionItem(item));
                    }
                    exploreView.loadMore(date, sectionItems);
                }
            };
            ApiQueryBuilder.getInstance().getBeforeDailyStories(date, subscriber);
        }
    }

}
