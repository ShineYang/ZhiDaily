package com.shineyang.zhidaily.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.api.ApiQueryBuilder;
import com.shineyang.zhidaily.bean.StoryContent;
import com.shineyang.zhidaily.iVew.IStoryView;

import rx.Subscriber;

/**
 * Created by ShineYang on 2017/1/19.
 */

public class StoryPresenter extends MvpBasePresenter<IStoryView> {
    public void getStoryContent(String id) {
        final IStoryView storyView = getView();
        if (storyView != null) {
            Subscriber<StoryContent> storyContentSubscriber = new Subscriber<StoryContent>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    storyView.showError();
                }

                @Override
                public void onNext(StoryContent storyContent) {
                    storyView.showStoryView(storyContent);
                }
            };

            ApiQueryBuilder.getInstance().getStoryContent(id, storyContentSubscriber);
        }

    }
}
