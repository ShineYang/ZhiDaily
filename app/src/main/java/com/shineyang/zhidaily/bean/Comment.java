package com.shineyang.zhidaily.bean;

/**
 * Created by ShineYang on 2017/4/7.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {
 * "comments": [
 * {
 * "author":"巨型黑娃儿",
 * "content":"也不算逻辑问题。其实小时候刚刚听说这个玩意的时候我也奇...",
 * "avatar":"http://pic3.zhimg.com/4131a3385c748c9e2d02ab80e29a0c52_im.jpg",
 * "time":1479706360,
 * "reply_to":{
 * "content":"第二个机灵抖的还是有逻辑问题，不该说忘了，应该说没喝过啊我也不知道",
 * "status":0,
 * "id":27275308,
 * "author":"2233155495"
 * },
 * "id":27276057,
 * "likes":2
 * },
 * ...
 * ]
 * }
 */

public class Comment {
    @Expose
    private List<CommentContent> comments;

    public List<CommentContent> getCommentContentList() {
        return comments;
    }

    public void setCommentContentList(List<CommentContent> commentContentList) {
        this.comments = commentContentList;
    }

    public class CommentContent {
        @Expose
        private String id;
        @Expose
        private String author;
        @Expose
        private String content;
        @Expose
        private String avatar;
        @Expose
        private String time;
        @Expose
        private String likes;
        @Expose
        private ReplyContent reply_to;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public ReplyContent getReplyTo() {
            return reply_to;
        }

        public void setReplyTo(ReplyContent replyTo) {
            this.reply_to = replyTo;
        }
    }

}
