package com.shineyang.zhidaily.adapter;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.SettingItem;
import com.shineyang.zhidaily.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/13.
 */

public class SettingAdapter extends BaseQuickAdapter<SettingItem, BaseViewHolder> {

    private OnSwitchClickListener onSwitchClickListener;

    public SettingAdapter(int layoutResId, List<SettingItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingItem item) {
        helper.setText(R.id.tv_setting_item, item.getItemName());
        helper.setImageResource(R.id.iv_setting_item, item.getIconResId());
        if (item.getIsNightModeSwitch()) {
            final SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(mContext);
            helper.getView(R.id.switch_night_mode).setVisibility(View.VISIBLE);
            SwitchCompat switchCompat = helper.getView(R.id.switch_night_mode);
            switchCompat.setChecked(sharedPreferencesUtils.getBoolean("is_night_mode"));
            switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        sharedPreferencesUtils.putBoolean("is_night_mode", true);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        onSwitchClickListener.onSwitchClick(sharedPreferencesUtils.getBoolean("is_night_mode"));
                    } else {
                        sharedPreferencesUtils.putBoolean("is_night_mode", false);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        onSwitchClickListener.onSwitchClick(sharedPreferencesUtils.getBoolean("is_night_mode"));
                    }
                }
            });
        } else {
            helper.getView(R.id.switch_night_mode).setVisibility(View.GONE);
        }
    }

    public void setOnSwitchClickListener(OnSwitchClickListener listener) {
        this.onSwitchClickListener = listener;
    }

    public interface OnSwitchClickListener {
        void onSwitchClick(Boolean isNightMode);
    }

}
