package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.bean.SettingItem;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/13.
 */

public interface IMoreView extends MvpView {
    void setSettingData(List<SettingItem> settingItems);
}
