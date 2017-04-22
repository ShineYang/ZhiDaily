package com.shineyang.zhidaily.utils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

import com.jaeger.library.StatusBarUtil;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ShineYang on 2017/1/16.
 */

public class AppUtils {
    private static final int TYPE_HEADER_DATE = 0;
    private static final int TYPE_COMMENT_DATE = 1;

    public static String formatDate(int type, String timeStamp) {
        String sfStr = "";
        switch (type) {
            case TYPE_HEADER_DATE:
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sf_header1 = new SimpleDateFormat("yyyyMMdd");
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sf_header2 = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    sfStr = sf_header2.format(sf_header1.parse(timeStamp));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case TYPE_COMMENT_DATE:
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                int i = Integer.parseInt(timeStamp);
                sfStr = sdf.format(new Date(i * 1000L));
                break;
        }

        return sfStr;
    }

    public static void showFloatingAlert(Activity activity, String alertText) {
        UIUtils.showFloatingAlert(activity, alertText);
    }

    public static void setTransparentBar(Activity activity) {
        StatusBarUtil.setTranslucent(activity, 112);
    }

}
