package com.shineyang.zhidaily.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.ColumnContentAdapter;
import com.shineyang.zhidaily.bean.Column;
import com.shineyang.zhidaily.iVew.IColumnContentListView;
import com.shineyang.zhidaily.presenter.ColumnContentListPresenter;
import com.shineyang.zhidaily.utils.AppUtils;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnContentListActivity extends MvpActivity<IColumnContentListView, ColumnContentListPresenter> implements IColumnContentListView {
    @BindView(R.id.toolbar_column)
    Toolbar toolbarColumnList;
    @BindView(R.id.rv_column_content_list)
    RecyclerView rvColumnContent;
    @BindView(R.id.tv_column_story_title)
    TextView tvColumnStoryTitle;
    @BindView(R.id.column_header_image)
    ImageView ivColumnContentImage;
    @BindView(R.id.column_list_loading_view)
    AVLoadingIndicatorView loadingView;

    private String id;
    private ColumnContentAdapter columnContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_content_list);
        ButterKnife.bind(this);
        AppUtils.setTransparentBar(this);
        getId();
        initRVColumnContent();
        showLoadingView();
        getPresenter().getCloumnContent(id);
    }

    @NonNull
    @Override
    public ColumnContentListPresenter createPresenter() {
        return new ColumnContentListPresenter();
    }


    public void getId() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    public void initRVColumnContent() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        columnContentAdapter = new ColumnContentAdapter(R.layout.layout_column_content_list_item, null);
        rvColumnContent.setLayoutManager(layoutManager);
        rvColumnContent.setHasFixedSize(true);
        rvColumnContent.setNestedScrollingEnabled(false);
        rvColumnContent.setAdapter(columnContentAdapter);
        rvColumnContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = columnContentAdapter.getItem(position).getId();
                if (id != null)
                    jumpToStoryContent(id);
            }
        });
    }


    public void jumpToStoryContent(String id) {
        Intent intent = new Intent(ColumnContentListActivity.this, StoryActivity.class);
        intent.putExtra("story_id", id);
        startActivity(intent);
    }

    public void loadHeaderInfo(final String title, String description, String imageUrl) {
        toolbarColumnList.setTitle(title);
        setSupportActionBar(toolbarColumnList);
        toolbarColumnList.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarColumnList.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_why:
                        new MaterialDialog.Builder(ColumnContentListActivity.this)
                                .title(getResources().getString(R.string.text_tip))
                                .content(getResources().getString(R.string.text_hot_column_list_tip))
                                .positiveText(getResources().getString(R.string.text_positive))
                                .show();

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        tvColumnStoryTitle.setText(description);
        Glide.with(this)
                .load(imageUrl).crossFade()
                .centerCrop()
                .into(ivColumnContentImage);
    }

    @Override
    public void showContentList(Column columnContent) {
        hideLoadingView();
        loadHeaderInfo(columnContent.getName(), columnContent.getDescription(), columnContent.getBackground());
        columnContentAdapter.setNewData(columnContent.getColumnContentList());
    }

    @Override
    public void onError() {
        hideLoadingView();
        AppUtils.showFloatingAlert(this, getResources().getString(R.string.text_get_column_list_failed));
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_column_list_toolbar, menu);


        return true;
    }

    public void showLoadingView() {
        loadingView.show();
    }

    public void hideLoadingView() {
        loadingView.hide();
    }
}
