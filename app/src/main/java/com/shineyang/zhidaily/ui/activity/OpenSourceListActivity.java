package com.shineyang.zhidaily.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.OpenSourceProjectsAdapter;
import com.shineyang.zhidaily.bean.OpenSourceProject;
import com.shineyang.zhidaily.iVew.IOpenSourceProjectsView;
import com.shineyang.zhidaily.presenter.OpenSourceProjectsPresenter;
import com.shineyang.zhidaily.utils.AppUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenSourceListActivity extends MvpActivity<IOpenSourceProjectsView, OpenSourceProjectsPresenter> implements IOpenSourceProjectsView {

    @BindView(R.id.toolbar_open_source)
    Toolbar toolBar;
    @BindView(R.id.rv_open_source)
    RecyclerView rvOpenSource;

    private OpenSourceProjectsAdapter openSourceProjectsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source_list);
        ButterKnife.bind(this);
        AppUtils.setTransparentBar(this);
        initToolbar();
        initRVOpenSource();
        getPresenter().setListData();
    }

    @NonNull
    @Override
    public OpenSourceProjectsPresenter createPresenter() {
        return new OpenSourceProjectsPresenter();
    }

    public void initToolbar() {
        toolBar.setTitle(getResources().getString(R.string.text_title_open_source));
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initRVOpenSource() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        openSourceProjectsAdapter = new OpenSourceProjectsAdapter(R.layout.layout_open_source_item, null);
        rvOpenSource.setLayoutManager(layoutManager);
        rvOpenSource.setHasFixedSize(true);
        rvOpenSource.setNestedScrollingEnabled(false);
        rvOpenSource.setAdapter(openSourceProjectsAdapter);
        rvOpenSource.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(openSourceProjectsAdapter.getItem(position).getProjectUrl());
                intent.setData(content_url);
                startActivity(intent);
            }
        });
    }


    @Override
    public void showList(OpenSourceProject openSourceProject) {
        openSourceProjectsAdapter.setNewData(openSourceProject.getOpenSourceProjectContentList());
    }
}
