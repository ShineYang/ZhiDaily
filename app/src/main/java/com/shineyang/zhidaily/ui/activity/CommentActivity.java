package com.shineyang.zhidaily.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.CommentAdapter;
import com.shineyang.zhidaily.bean.Comment;
import com.shineyang.zhidaily.iVew.ICommentView;
import com.shineyang.zhidaily.presenter.CommentPresenter;
import com.shineyang.zhidaily.utils.AppUtils;
import com.wang.avi.AVLoadingIndicatorView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends MvpActivity<ICommentView, CommentPresenter> implements ICommentView {
    @BindView(R.id.toolbar_comment)
    Toolbar toolBar;
    @BindView(R.id.rv_comment)
    RecyclerView rvLongComment;
    @BindView(R.id.rl_check_short_comment)
    RelativeLayout rlCheckShortComment;
    @BindView(R.id.ll_no_comment_view)
    LinearLayout llNoCommentView;
    @BindView(R.id.comment_loading_view)
    AVLoadingIndicatorView loadingView;

    private String storyId;
    private BaseQuickAdapter commentAdapter;
    private List<Comment.CommentContent> tempComment = null;
    private Boolean hasLongComment = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        AppUtils.setTransparentBar(this);
        initToolbar();
        getStoryId();
        showLoadingView();
        getPresenter().getLongComment(storyId);
    }

    @NonNull
    @Override
    public CommentPresenter createPresenter() {
        return new CommentPresenter();
    }

    public void initToolbar() {
        toolBar.setTitle(getResources().getString(R.string.text_comment));
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getStoryId() {
        Intent intent = getIntent();
        storyId = intent.getStringExtra("id");
    }

    public void initView(Comment longCommentList) {
        commentAdapter = new CommentAdapter(R.layout.layout_comment_item, longCommentList.getCommentContentList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        rvLongComment.setLayoutManager(layoutManager);
        rvLongComment.setNestedScrollingEnabled(false);
        rvLongComment.setAdapter(commentAdapter);

        rlCheckShortComment.setVisibility(View.VISIBLE);
        rlCheckShortComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().getShortComment(storyId, hasLongComment);
            }
        });

    }

    @Override
    public void showLongComment(Comment longCommentList) {
        if (longCommentList.getCommentContentList().size() == 0) {
            hasLongComment = false;
            getPresenter().getShortComment(storyId, hasLongComment);
        } else {
            tempComment = longCommentList.getCommentContentList();
            initView(longCommentList);
        }
    }

    @Override
    public void showShortComment(Boolean hasLongComment, Comment shortCommentList) {
        if (hasLongComment) {
            rlCheckShortComment.setVisibility(View.GONE);
            tempComment.addAll(shortCommentList.getCommentContentList());
            commentAdapter.notifyDataSetChanged();
            commentAdapter.loadMoreComplete();
        } else {
            initView(shortCommentList);
            rlCheckShortComment.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        AppUtils.showFloatingAlert(this, getResources().getString(R.string.text_get_comment_list_failed));
    }

    public void showLoadingView() {
        rlCheckShortComment.setVisibility(View.INVISIBLE);
        loadingView.show();
    }

    @Override
    public void hideLoadingView() {
        loadingView.hide();
    }

    @Override
    public void showNoComment() {
        llNoCommentView.setVisibility(View.VISIBLE);
    }


}
