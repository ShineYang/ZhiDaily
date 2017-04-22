package com.shineyang.zhidaily.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ShineYang on 2017/4/7.
 */

public class ReplyContent {
    /**
     * "reply_to":{
     * "content":"第二个机灵抖的还是有逻辑问题，不该说忘了，应该说没喝过啊我也不知道",
     * "status":0,
     * "id":27275308,
     * "author":"2233155495"
     * },
     */
    @Expose
    @SerializedName("author")
    private String replyToAuthor;

    @Expose
    @SerializedName("content")
    private String replyToContent;

    public String getReplyToAuthor() {
        return replyToAuthor;
    }

    public void setReplyToAuthor(String replyToAuthor) {
        this.replyToAuthor = replyToAuthor;
    }

    public String getReplyToContent() {
        return replyToContent;
    }

    public void setReplyToContent(String replyToContent) {
        this.replyToContent = replyToContent;
    }
}
