package com.shineyang.zhidaily.bean;

/**
 * Created by ShineYang on 2017/4/20.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {
 * "timestamp":1491001198,
 * "stories":[{
 * "images":[
 * "https://pic1.zhimg.com/v2-8b4d89bafe6e9a323d7bc57985a823cc.jpg"
 * ],
 * "date":"20170401",
 * "display_date":"4 月 1 日",
 * "id":9325407,
 * "title":"瞎扯 · 如何正确地吐槽"
 * }
 * ...
 * ],
 * "name":"瞎扯"
 * }
 */

public class HotColumn {
    @Expose
    private String name;
    @Expose
    private String timestamp;
    @Expose
    private List<HotColumnStory> stories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<HotColumnStory> getStories() {
        return stories;
    }

    public void setStories(List<HotColumnStory> stories) {
        this.stories = stories;
    }

    public class HotColumnStory {
        @Expose
        private String title;
        @Expose
        private String id;
        @Expose
        private String date;
        @Expose
        @SerializedName("display_date")
        private String displayDate;
        @Expose
        private String[] images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDisplayDate() {
            return displayDate;
        }

        public void setDisplayDate(String displayDate) {
            this.displayDate = displayDate;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }
    }

}
