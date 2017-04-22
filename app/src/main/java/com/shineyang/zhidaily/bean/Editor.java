package com.shineyang.zhidaily.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by ShineYang on 2017/2/5.
 */

public class Editor {
    @Expose
    private String id;
    @Expose
    private String avatar;
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
