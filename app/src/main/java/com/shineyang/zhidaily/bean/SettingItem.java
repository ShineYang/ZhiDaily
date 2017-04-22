package com.shineyang.zhidaily.bean;

/**
 * Created by ShineYang on 2017/4/13.
 */

public class SettingItem {
    private String itemName;
    private int iconResId;
    private Boolean isNightModeSwitch;

    public Boolean getIsNightModeSwitch() {
        return isNightModeSwitch;
    }

    public void setIsNightModeSwitch(Boolean nightModeSwitch) {
        isNightModeSwitch = nightModeSwitch;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
