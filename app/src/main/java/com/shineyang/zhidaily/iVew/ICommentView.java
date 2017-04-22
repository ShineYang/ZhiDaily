package com.shineyang.zhidaily.iVew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.zhidaily.bean.Comment;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/7.
 */

public interface ICommentView extends MvpView {
    void showLongComment(Comment longCommentList);

    void showShortComment(Boolean hasLongComment,Comment shortCommentList);

    void showError();

    void hideLoadingView();

    void showNoComment();

}
