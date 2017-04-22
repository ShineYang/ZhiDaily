package com.shineyang.zhidaily.api;

import com.shineyang.zhidaily.bean.Column;
import com.shineyang.zhidaily.bean.Comment;
import com.shineyang.zhidaily.bean.DailyStories;
import com.shineyang.zhidaily.bean.HotColumn;
import com.shineyang.zhidaily.bean.PastColumns;
import com.shineyang.zhidaily.bean.SplashInfo;
import com.shineyang.zhidaily.bean.StoryContent;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ShineYang on 2017/1/15.
 */

public class ApiQueryBuilder {
    private static final String BASE_API_URL = "http://news-at.zhihu.com/api/";
    private static final int DEFAULT_TIMEOUT = 10;
    private static ApiClient apiClient;

    private ApiQueryBuilder() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_API_URL)
                .build();

        apiClient = retrofit.create(ApiClient.class);
    }

    //在访问QueryBuilder时创建单例
    private static class SingletonHolder {
        private static final ApiQueryBuilder INSTANCE = new ApiQueryBuilder();
    }


    public static ApiQueryBuilder getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 获取 splash 图片
     *
     * @param subscriber subscriber
     */
    public void getSplashImage(int width, int height, Subscriber<SplashInfo> subscriber) {
        apiClient.getSplashImage(width, height)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 获取日报内容 (包括banner和列表)
     *
     * @param subscriber subscriber
     */
    public void getLatestStories(Subscriber<DailyStories> subscriber) {
        apiClient.getLatestStories()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 获取某日期前一天的日报内容
     *
     * @param date       date
     * @param subscriber subscriber
     */
    public void getBeforeDailyStories(String date, Subscriber<DailyStories> subscriber) {
        apiClient.getBeforeDailyStories(date)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 根据id获取日报详细内容
     *
     * @param id         id
     * @param subscriber subscriber
     */
    public void getStoryContent(String id, Subscriber<StoryContent> subscriber) {
        apiClient.getStoryContent(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 根据id获取日报评长评
     *
     * @param id         id
     * @param subscriber subscriber
     */
    public void getStoryLongComment(String id, Subscriber<Comment> subscriber) {
        apiClient.getStoryLongComment(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 根据id获取日报短评
     *
     * @param id         id
     * @param subscriber subscriber
     */
    public void getStoryShortComment(String id, Subscriber<Comment> subscriber) {
        apiClient.getStoryShortComments(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 获取主题列表
     *
     * @param subscriber subscriber
     */
    public void getPastColumn(Subscriber<PastColumns> subscriber) {
        apiClient.getPastColumn()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 获取主题内容列表
     *
     * @param subscriber subscriber
     */
    public void getPastColumnContentList(String id, Subscriber<Column> subscriber) {
        apiClient.getPastColumnDetialList(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取热门栏目内容列表
     *
     * @param subscriber subscriber
     * @param id         id
     */
    public void getHotColumnList(String id, Subscriber<HotColumn> subscriber) {
        apiClient.getHotColumnList(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 获取往期热门栏目内容列表
     *
     * @param subscriber subscriber
     * @param id         id
     * @param timeStamp  timestamp
     */
    public void getPastHotColumnList(String id, String timeStamp, Subscriber<HotColumn> subscriber) {
        apiClient.getPastHotColumnList(id, timeStamp)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
