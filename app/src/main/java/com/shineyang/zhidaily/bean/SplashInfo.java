package com.shineyang.zhidaily.bean;

/**
 * Created by ShineYang on 2017/4/13.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {
 * "creatives": [
 * {
 * "url": "https://pic3.zhimg.com/v2-5af460972557190bd4306ad66f360d4a.jpg",
 * "start_time": 1491927091,
 * "impression_tracks": [
 * "https://sugar.zhihu.com/track?vs=1&ai=3838&ut=&cg=2&ts=1491927091.68&si=52848dcc42b046bbb2d7a40921268157&lu=0&hn=ad-engine.ad-engine.7fab952b&at=impression&pf=PC&az=11&sg=4590d6230fc8f471ada5f10e6f27be94"
 * ],
 * "type": 0,
 * "id": "3838"
 * }
 * ]
 * }
 */

public class SplashInfo {

    @Expose
    @SerializedName("creatives")
    private List<SplashViewInfo> splashViewInfoList;

    public List<SplashViewInfo> getSplashViewInfoList() {
        return splashViewInfoList;
    }

    public void setSplashViewInfoList(List<SplashViewInfo> splashViewInfoList) {
        this.splashViewInfoList = splashViewInfoList;
    }

    public class SplashViewInfo {
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

}
