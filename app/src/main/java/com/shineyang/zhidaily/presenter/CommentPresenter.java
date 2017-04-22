package com.shineyang.zhidaily.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.zhidaily.api.ApiQueryBuilder;
import com.shineyang.zhidaily.bean.Comment;
import com.shineyang.zhidaily.iVew.ICommentView;


import rx.Subscriber;

/**
 * Created by ShineYang on 2017/4/7.
 */

public class CommentPresenter extends MvpBasePresenter<ICommentView> {


    public void getLongComment(String id) {
        final ICommentView commentView = getView();
        if (commentView != null) {
            Subscriber<Comment> subscriber = new Subscriber<Comment>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    commentView.hideLoadingView();
                    commentView.showError();
                }

                @Override
                public void onNext(Comment commentList) {
                    commentView.hideLoadingView();
                    commentView.showLongComment(commentList);
                }
            };
            ApiQueryBuilder.getInstance().getStoryLongComment(id, subscriber);
        }
    }


    public void getShortComment(String id, final Boolean hasLongComment) {
        final ICommentView commentView = getView();
        if (commentView != null) {
            Subscriber<Comment> subscriber = new Subscriber<Comment>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    commentView.hideLoadingView();
                    commentView.showError();
                }

                @Override
                public void onNext(Comment commentList) {
                    if (commentList.getCommentContentList().size() == 0) {
                        commentView.showNoComment();
                    } else {
                        commentView.hideLoadingView();
                        commentView.showShortComment(hasLongComment, commentList);
                    }
                }
            };
            ApiQueryBuilder.getInstance().getStoryShortComment(id, subscriber);
        }
    }
}
