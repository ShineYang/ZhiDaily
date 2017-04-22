package com.shineyang.zhidaily.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.PastColumnAdapter;
import com.shineyang.zhidaily.bean.PastColumns;
import com.shineyang.zhidaily.iVew.IChannelView;
import com.shineyang.zhidaily.presenter.ChannelPresenter;
import com.shineyang.zhidaily.ui.activity.ColumnContentListActivity;
import com.shineyang.zhidaily.ui.activity.HotColumnActivity;
import com.shineyang.zhidaily.utils.AppUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ShineYang on 2017/1/15.
 */

public class FragmentChannel extends MvpFragment<IChannelView, ChannelPresenter> implements IChannelView {

    @BindView(R.id.cv_xiache)
    CardView cvXiaChe;
    @BindView(R.id.cv_jingqi)
    CardView cvJingQi;
    @BindView(R.id.rv_past_column)
    RecyclerView rvPastColumn;
    @BindView(R.id.channel_loading_view)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.ll_refresh_again)
    LinearLayout llRefreshAgain;
    @BindView(R.id.ll_main_channel_container)
    LinearLayout llMainChannelContainer;

    private PastColumnAdapter pastColumnAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_channel, container, false);
    }

    @NonNull
    @Override
    public ChannelPresenter createPresenter() {
        return new ChannelPresenter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initHotColumnLisenter();
        initRVPastColumnView();
        getPresenter().getPastColumnList();
        showLoadingView();
    }

    public void initHotColumnLisenter() {
        cvXiaChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotColumnActivity.class);
                intent.putExtra("id", "2");
                startActivity(intent);
            }
        });

        cvJingQi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotColumnActivity.class);
                intent.putExtra("id", "1");
                startActivity(intent);
            }
        });
    }


    public void initRVPastColumnView() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        pastColumnAdapter = new PastColumnAdapter(R.layout.layout_past_column_item, null);
        rvPastColumn.setLayoutManager(layoutManager);
        rvPastColumn.setHasFixedSize(true);
        rvPastColumn.setNestedScrollingEnabled(false);
        rvPastColumn.setAdapter(pastColumnAdapter);
        rvPastColumn.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ColumnContentListActivity.class);
                intent.putExtra("id", pastColumnAdapter.getItem(position).getId());
                startActivity(intent);
            }
        });
    }

    public void initRVPastColumn(List<PastColumns.PastColumnContent> pastColumnContentList) {
        pastColumnAdapter.setNewData(pastColumnContentList);
    }


    @Override
    public void showPastList(List<PastColumns.PastColumnContent> pastColumnContentList) {
        llRefreshAgain.setVisibility(View.INVISIBLE);
        llMainChannelContainer.setVisibility(View.VISIBLE);
        initRVPastColumn(pastColumnContentList);
        hideLoadingView();
    }

    @Override
    public void onError() {
        hideLoadingView();
        llRefreshAgain.setVisibility(View.VISIBLE);
        AppUtils.showFloatingAlert(getActivity(), getResources().getString(R.string.text_get_comment_list_failed));
        llRefreshAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().getPastColumnList();
            }
        });

    }

    public void showLoadingView() {
        loadingView.show();
    }

    public void hideLoadingView() {
        loadingView.hide();
    }
}
