package com.shineyang.zhidaily.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/21.
 */

public class OpenSourceProject {

    private List<OpenSourceProjectContent> projects;

    public List<OpenSourceProjectContent> getOpenSourceProjectContentList() {
        return projects;
    }

    public void setOpenSourceProjectContentList(List<OpenSourceProjectContent> openSourceProjectContentList) {
        this.projects = openSourceProjectContentList;
    }

    public class OpenSourceProjectContent {
        @SerializedName("name")
        private String projectName;
        @SerializedName("license")
        private String projectLicense;
        @SerializedName("author")
        private String projectAuthor;
        @SerializedName("url")
        private String projectUrl;

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectLicense() {
            return projectLicense;
        }

        public void setProjectLicense(String projectLicense) {
            this.projectLicense = projectLicense;
        }

        public String getProjectAuthor() {
            return projectAuthor;
        }

        public void setProjectAuthor(String projectAuthor) {
            this.projectAuthor = projectAuthor;
        }

        public String getProjectUrl() {
            return projectUrl;
        }

        public void setProjectUrl(String projectUrl) {
            this.projectUrl = projectUrl;
        }
    }

}
