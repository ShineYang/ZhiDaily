package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.bean.StoryContent;

/**
 * Created by ShineYang on 2017/1/19.
 */

public interface IStoryView extends MvpView{
    void showStoryView(StoryContent storyContent);
    void showError();
}
