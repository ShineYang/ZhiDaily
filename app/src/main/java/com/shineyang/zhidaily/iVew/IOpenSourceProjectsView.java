package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.bean.OpenSourceProject;

/**
 * Created by ShineYang on 2017/4/21.
 */

public interface IOpenSourceProjectsView extends MvpView {
    void showList(OpenSourceProject openSourceProject);
}
