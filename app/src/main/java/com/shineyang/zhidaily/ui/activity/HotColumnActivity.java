package com.shineyang.zhidaily.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.HotColumnAdapter;
import com.shineyang.zhidaily.bean.HotColumn;
import com.shineyang.zhidaily.iVew.IHotColumnView;
import com.shineyang.zhidaily.presenter.HotColumnPresenter;
import com.shineyang.zhidaily.utils.AppUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class HotColumnActivity extends MvpActivity<IHotColumnView, HotColumnPresenter> implements IHotColumnView {

    @BindView(R.id.toolbar_hot_column)
    Toolbar toolBar;
    @BindView(R.id.rv_hot_column)
    RecyclerView rvHotColumn;

    private String id;
    private HotColumnAdapter hotColumnAdapter;
    private TextView tvFooter;
    private String tempTimeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_column);
        ButterKnife.bind(this);
        AppUtils.setTransparentBar(this);
        getId();
        initToolBar(id);
        initRVColumnContent();
        getPresenter().getLatestList(id);
    }

    @NonNull
    @Override
    public HotColumnPresenter createPresenter() {
        return new HotColumnPresenter();
    }

    public void getId() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    public void initToolBar(String id) {
        switch (id) {
            case "1":
                toolBar.setTitle(getResources().getString(R.string.text_title_jingqi));
                break;
            case "2":
                toolBar.setTitle(getResources().getString(R.string.text_title_xiache));
                break;
            default:
                break;
        }
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initRVColumnContent() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        hotColumnAdapter = new HotColumnAdapter(R.layout.layout_hot_column_item, null);
        rvHotColumn.setLayoutManager(layoutManager);
        rvHotColumn.setHasFixedSize(true);
        rvHotColumn.setNestedScrollingEnabled(false);
        rvHotColumn.setAdapter(hotColumnAdapter);
        rvHotColumn.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpToStoryContent(hotColumnAdapter.getItem(position).getId());
            }
        });
    }

    public void jumpToStoryContent(String id) {
        Intent intent = new Intent(HotColumnActivity.this, StoryActivity.class);
        intent.putExtra("story_id", id);
        startActivity(intent);
    }

    @Override
    public void showContentList(final HotColumn hotColumn) {
        tempTimeStamp = hotColumn.getTimestamp();
        hotColumnAdapter.setNewData(hotColumn.getStories());
        hotColumnAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        hotColumnAdapter.addFooterView(getFooterView());
        tvFooter = (TextView) hotColumnAdapter.getFooterLayout().findViewById(R.id.tv_footer);
        tvFooter.setText(getResources().getString(R.string.text_footer_click_loading_more));
        hotColumnAdapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFooter.setText(getResources().getString(R.string.text_footer_loading));
                getPresenter().getPastList(id, tempTimeStamp);
            }
        });
    }

    public View getFooterView() {
        return getLayoutInflater().inflate(R.layout.layout_footer_load_old_content, (ViewGroup) rvHotColumn.getParent(), false);
    }

    @Override
    public void onLoadMore(HotColumn hotColumn) {
        tempTimeStamp = hotColumn.getTimestamp();
        hotColumnAdapter.addData(hotColumn.getStories());
        hotColumnAdapter.loadMoreComplete();
        tvFooter.setText(getResources().getString(R.string.text_footer_click_loading_more));
    }

    @Override
    public void onError() {
        AppUtils.showFloatingAlert(this, getResources().getString(R.string.text_get_comment_list_failed));
    }

    @Override
    public void onLoadMoreError() {
        AppUtils.showFloatingAlert(this, getResources().getString(R.string.text_get_comment_list_failed));
    }
}
