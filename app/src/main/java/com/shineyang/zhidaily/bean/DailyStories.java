package com.shineyang.zhidaily.bean;


import java.util.List;

/**
 * Created by ShineYang on 2017/1/15.
 */

public class DailyStories {
    private String date;
    private List<Story> stories;
    private List<BannerStory> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStoryList() {
        return stories;
    }

    public void setStoryList(List<Story> storyList) {
        this.stories = storyList;
    }

    public List<BannerStory> getBannerStoryList() {
        return top_stories;
    }

    public void setBannerStoryList(List<BannerStory> bannerStoryList) {
        this.top_stories = bannerStoryList;
    }

}
