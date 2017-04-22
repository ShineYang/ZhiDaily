package com.shineyang.zhidaily.bean;

import java.util.List;

/**
 * Created by ShineYang on 2017/1/15.
 */

public class TopBanners {
    private List<String> titles;
    private List<String> images;
    private List<String> idList;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

}
