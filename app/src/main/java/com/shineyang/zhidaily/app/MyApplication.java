package com.shineyang.zhidaily.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.shineyang.zhidaily.utils.SharedPreferencesUtils;


/**
 * Created by ShineYang on 2017/1/31.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(getApplicationContext());
        if (sharedPreferencesUtils.getBoolean("is_night_mode")) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
