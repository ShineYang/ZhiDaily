package com.shineyang.zhidaily.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ShineYang on 2017/1/19.
 */

public class StoryContent {
    @Expose
    private List<String> images;
    @Expose
    private String type;
    @Expose
    private String id;
    @Expose
    @SerializedName("ga_prefix")
    private String gaPrefix;
    @Expose
    private String title;
    @Expose
    @SerializedName("multipic")
    private String multiPic;
    @Expose
    private String image;
    @Expose
    @SerializedName("share_url")
    private String shareUrl;
    @Expose
    private String body;
    @Expose
    @SerializedName("image_source")
    private String imageSource;
    @Expose
    @SerializedName("js")
    private List<String> jsList;
    @Expose
    @SerializedName("css")
    private List<String> cssList;
    @Expose
    private List<Editor> recommenders;
    @Expose

    private String thumbnail;
    private String url;
    private String sectionThumbnail;
    private String sectionId;
    private String sectionName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMultiPic() {
        return multiPic;
    }

    public void setMultiPic(String multiPic) {
        this.multiPic = multiPic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public List<String> getJsList() {
        return jsList;
    }

    public void setJsList(List<String> jsList) {
        this.jsList = jsList;
    }

    public List<String> getCssList() {
        return cssList;
    }

    public void setCssList(List<String> cssList) {
        this.cssList = cssList;
    }

    public List<Editor> getRecommenders() {
        return recommenders;
    }

    public void setRecommenders(List<Editor> recommenders) {
        this.recommenders = recommenders;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSectionThumbnail() {
        return sectionThumbnail;
    }

    public void setSectionThumbnail(String sectionThumbnail) {
        this.sectionThumbnail = sectionThumbnail;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
