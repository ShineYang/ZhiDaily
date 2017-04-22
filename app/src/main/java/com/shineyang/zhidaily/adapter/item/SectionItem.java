package com.shineyang.zhidaily.adapter.item;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by ShineYang on 2017/1/17.
 */

public class SectionItem extends SectionEntity {
    private String dateString;
    private String storyId;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }


    public SectionItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionItem(MainStoryItem item) {
        super(item);
        this.storyId = item.getId();
    }

    public String getStoryId(){
        return storyId;
    }
}
