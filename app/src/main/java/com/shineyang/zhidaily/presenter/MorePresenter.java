package com.shineyang.zhidaily.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.SettingItem;
import com.shineyang.zhidaily.iVew.IMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShineYang on 2017/4/13.
 */

public class MorePresenter extends MvpBasePresenter<IMoreView> {
    private static final int SETTING_ITEM_SIZE = 4;
    private String[] settingItemTitles = {"夜间模式", "意见反馈", "开源许可", "关于"};
    private int[] settingIconRes = {R.mipmap.ic_night_mode, R.mipmap.ic_feedback, R.mipmap.ic_github, R.mipmap.ic_about};
    private List<SettingItem> settingItemList = new ArrayList<>();

    public void setSettingItemData() {
        final IMoreView moreView = getView();
        if (moreView != null) {
            for (int i = 0; i < SETTING_ITEM_SIZE; i++) {
                SettingItem item = new SettingItem();
                item.setItemName(settingItemTitles[i]);
                item.setIconResId(settingIconRes[i]);
                if (i == 0) {
                    item.setIsNightModeSwitch(true);
                } else {
                    item.setIsNightModeSwitch(false);
                }
                settingItemList.add(item);
            }
            moreView.setSettingData(settingItemList);
        }
    }
}
