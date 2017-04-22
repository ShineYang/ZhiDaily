package com.shineyang.zhidaily.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/11.
 */

public class PastColumns {

    @Expose
    private List<PastColumnContent> others;

    public List<PastColumnContent> getOthers() {
        return others;
    }

    public void setOthers(List<PastColumnContent> others) {
        this.others = others;
    }

    public class PastColumnContent {

        /**
         * "color":15007,
         * "thumbnail":"http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg",
         * "description":"了解自己和别人，了解彼此的欲望和局限。",
         * "id":13,
         * "name":"日常心理学"
         */

        @Expose
        private String thumbnail;
        @Expose
        private String description;
        @Expose
        private String id;
        @Expose
        private String name;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
