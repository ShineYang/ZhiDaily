package com.shineyang.zhidaily.bean;

/**
 * Created by ShineYang on 2017/4/18.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {
 * stories: [
 * {
 * images: [
 * "http://pic1.zhimg.com/84dadf360399e0de406c133153fc4ab8_t.jpg"
 * ],
 * type: 0,
 * id: 4239728,
 * title: "前苏联监狱纹身百科图鉴"
 * },
 * ...
 * ],
 * <p>
 * description: "为你发现最有趣的新鲜事，建议在 WiFi 下查看",
 * background: "http://pic1.zhimg.com/a5128188ed788005ad50840a42079c41.jpg",
 * color: 8307764,
 * name: "不许无聊",
 * image: "http://pic3.zhimg.com/da1fcaf6a02d1223d130d5b106e828b9.jpg",
 * editors: [
 * {
 * url: "http://www.zhihu.com/people/wezeit",
 * bio: "微在 Wezeit 主编",
 * id: 70,
 * avatar: "http://pic4.zhimg.com/068311926_m.jpg",
 * name: "益康糯米"
 * },
 * ...
 * ],
 * image_source: ""
 * }
 * <p>
 * stories : 该主题日报中的文章列表
 * images : 图像地址（其类型为数组。请留意在代码中处理无该属性与数组长度为 0 的情况）
 * type : 类型，作用未知
 * title : 消息的标题
 * description : 该主题日报的介绍
 * background : 该主题日报的背景图片（大图）
 * color : 颜色，作用未知
 * name : 该主题日报的名称
 * image : 背景图片的小图版本
 * editors : 该主题日报的编辑（『用户推荐日报』中此项的指是一个空数组，在 App 中的主编栏显示为『许多人』，点击后访问该主题日报的介绍页面，请留意）
 * url : 主编的知乎用户主页
 * bio : 主编的个人简介
 * id : 数据库中的唯一表示符
 * avatar : 主编的头像
 * name : 主编的姓名
 * image_source : 图像的版权信息
 */

public class Column {
    @Expose
    @SerializedName("stories")
    private List<ColumnContent> columnContentList;
    @Expose
    private String description;
    @Expose
    private String background;
    @Expose
    private String name;

    public List<ColumnContent> getColumnContentList() {
        return columnContentList;
    }

    public void setColumnContentList(List<ColumnContent> columnContentList) {
        this.columnContentList = columnContentList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class ColumnContent {
        @Expose
        private List<String> images;
        @Expose
        private String type;
        @Expose
        private String id;
        @Expose
        private String title;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}
