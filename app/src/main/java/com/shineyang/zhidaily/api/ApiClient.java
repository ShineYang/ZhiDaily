package com.shineyang.zhidaily.api;


import com.shineyang.zhidaily.bean.Column;
import com.shineyang.zhidaily.bean.Comment;
import com.shineyang.zhidaily.bean.DailyStories;
import com.shineyang.zhidaily.bean.HotColumn;
import com.shineyang.zhidaily.bean.PastColumns;
import com.shineyang.zhidaily.bean.SplashInfo;
import com.shineyang.zhidaily.bean.StoryContent;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ShineYang on 2017/1/15.
 */

public interface ApiClient {
    //启动图
    //http://news-at.zhihu.com/api/7/prefetch-launch-images/1080*1920
    @GET("7/prefetch-launch-images/{width}*{height}")
    Observable<SplashInfo> getSplashImage(@Path("width") int width, @Path("height") int height);

    //最新内容
    @GET("4/news/latest")
    Observable<DailyStories> getLatestStories();

    //内容详情
    @GET("4/news/{Id}")
    Observable<StoryContent> getStoryContent(@Path("Id") String Id);

    //前日新闻
    @GET("4/news/before/{date}")
    Observable<DailyStories> getBeforeDailyStories(@Path("date") String date);

    //该条新闻的额外信息 (评论数量 / 点赞数量)
    @GET("4/story-extra/{Id}")
    Observable<StoryContent> getStoryExtraInfo(@Path("Id") String Id);

    //对应长评
    //http://news-at.zhihu.com/api/4/story/8997528/long-comments
    @GET("4/story/{Id}/long-comments")
    Observable<Comment> getStoryLongComment(@Path("Id") String Id);

    //短评
    @GET("4/story/{Id}/short-comments")
    Observable<Comment> getStoryShortComments(@Path("Id") String Id);

    //主题列表
    //http://news-at.zhihu.com/api/4/themes
    @GET("4/themes")
    Observable<PastColumns> getPastColumn();

    //主题日报内容查看
    //http://news-at.zhihu.com/api/4/theme/11
    @GET("4/theme/{Id}")
    Observable<Column> getPastColumnDetialList(@Path("Id") String Id);

    //栏目具体消息查看（1：深夜惊奇 2：瞎扯）
    //http://news-at.zhihu.com/api/3/section/1
    @GET("3/section/{Id}")
    Observable<HotColumn> getHotColumnList(@Path("Id") String Id);

    //栏目往期具体消息查看（1：深夜惊奇 2：瞎扯）
    //http://news-at.zhihu.com/api/3/section/1
    @GET("3/section/{Id}/before/{timestamp}")
    Observable<HotColumn> getPastHotColumnList(@Path("Id") String Id, @Path("timestamp") String timeStamp);

}
