package com.shineyang.zhidaily.presenter;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.bean.OpenSourceProject;
import com.shineyang.zhidaily.iVew.IOpenSourceProjectsView;


/**
 * Created by ShineYang on 2017/4/21.
 */

public class OpenSourceProjectsPresenter extends MvpBasePresenter<IOpenSourceProjectsView> {
    public void setListData() {
        IOpenSourceProjectsView openSourceProjectsView = getView();
        if (openSourceProjectsView != null) {
            OpenSourceProject openSourceProject;
            Gson gson = new Gson();
            String jsonStr = "{\n" +
                    "    \"projects\":[\n" +
                    "        {\n" +
                    "            \"name\":\"知乎日报 API 分析\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright Zhihu.Inc.\",\n" +
                    "            \"url\":\"https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"ButterKnife\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright 2013 Jake Wharton.\",\n" +
                    "            \"url\":\"https://github.com/JakeWharton/butterknife\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"Alerter\",\n" +
                    "            \"license\":\"MIT\",\n" +
                    "            \"author\":\"Copyright 2016 Tapadoo, Dublin.\",\n" +
                    "            \"url\":\"https://github.com/Tapadoo/Alerter\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"AVLoadingIndicatorView\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright 2015 jack wang.\",\n" +
                    "            \"url\":\"https://github.com/81813780/AVLoadingIndicatorView\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"BaseRecyclerViewAdapterHelper\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright 2016 陈宇明.\",\n" +
                    "            \"url\":\"https://github.com/CymChad/BaseRecyclerViewAdapterHelper\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"Mosby\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright 2015 Hannes Dorfmann.\",\n" +
                    "            \"url\":\"https://github.com/sockeqwe/mosby\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"Material-Dialogs\",\n" +
                    "            \"license\":\"MIT\",\n" +
                    "            \"author\":\"Copyright 2017 afollestad.\",\n" +
                    "            \"url\":\"https://github.com/afollestad/material-dialogs\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"RxAndroid\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright 2015 The RxAndroid authors.\",\n" +
                    "            \"url\":\"https://github.com/ReactiveX/RxAndroid\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"RxJava\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright 2016-present, RxJava Contributors\",\n" +
                    "            \"url\":\"https://github.com/ReactiveX/RxJava\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"Retrofit\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright 2013 Square, Inc.\",\n" +
                    "            \"url\":\"https://github.com/square/retrofit\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"Okhttp\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright Square, Inc.\",\n" +
                    "            \"url\":\"https://github.com/square/okhttp\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"Glide\",\n" +
                    "            \"license\":\"BSD, part MIT and Apache 2.0\",\n" +
                    "            \"author\":\"Copyright Sam Judd - sjudd, samajudd.\",\n" +
                    "            \"url\":\"https://github.com/bumptech/glide\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\":\"Banner\",\n" +
                    "            \"license\":\"Apache 2.0\",\n" +
                    "            \"author\":\"Copyright youth5201314.\",\n" +
                    "            \"url\":\"https://github.com/youth5201314/banner\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            openSourceProject = gson.fromJson(jsonStr, OpenSourceProject.class);
            openSourceProjectsView.showList(openSourceProject);
        }
    }
}
