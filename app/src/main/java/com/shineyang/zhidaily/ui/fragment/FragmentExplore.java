package com.shineyang.zhidaily.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.MainStoryAdapter;
import com.shineyang.zhidaily.adapter.item.SectionItem;
import com.shineyang.zhidaily.bean.TopBanners;
import com.shineyang.zhidaily.iVew.IExploreView;
import com.shineyang.zhidaily.presenter.ExploreViewPresenter;
import com.shineyang.zhidaily.ui.activity.StoryActivity;
import com.shineyang.zhidaily.ui.bridge.OnBottomBarReselectListener;
import com.shineyang.zhidaily.utils.AppUtils;
import com.shineyang.zhidaily.utils.GlideImageLoader;
import com.wang.avi.AVLoadingIndicatorView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ShineYang on 2017/1/15.
 */

public class FragmentExplore extends MvpFragment<IExploreView, ExploreViewPresenter> implements
        IExploreView, SwipeRefreshLayout.OnRefreshListener, OnBottomBarReselectListener {

    @BindView(R.id.nsv_main_scrollview)
    NestedScrollView nestedScrollView;
    @BindView(R.id.view_banner)
    Banner banner;
    @BindView(R.id.rv_story_list)
    RecyclerView rvStoryList;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView loadingView;

    private MainStoryAdapter mainStoryAdapter;
    private List<SectionItem> tempMainStoryItemWithHeader = null;
    private String tempDate;
    private TextView tvFooter;
    private boolean isRefreshing = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_explore, container, false);
    }

    @NonNull
    @Override
    public ExploreViewPresenter createPresenter() {
        return new ExploreViewPresenter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initSwipeRefresh();
        initRVStoryList();
        getPresenter().fetchData(isRefreshing);
        showLoadingView();
    }

    public void initSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.grey500));
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setProgressViewEndTarget(true, 300);
    }

    @Override
    public void onRefresh() {
        if (!isRefreshing) {
            isRefreshing = true;
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    getPresenter().fetchData(isRefreshing);
                }
            });
        }
    }

    public void initRVStoryList() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        mainStoryAdapter = new MainStoryAdapter(R.layout.layout_story_rv_item, R.layout.layout_date_header, null);
        rvStoryList.setLayoutManager(layoutManager);
        rvStoryList.setHasFixedSize(true);
        rvStoryList.setNestedScrollingEnabled(false);
        rvStoryList.setAdapter(mainStoryAdapter);
        rvStoryList.addOnItemTouchListener(new OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = mainStoryAdapter.getItem(position).getStoryId();
                if (id != null)
                    jumpToStoryContent(id);
            }
        });
    }

    public void initMainStoryView(final String date, List<SectionItem> mainStoryItemListWithHeader) {
        tempDate = date;
        tempMainStoryItemWithHeader = mainStoryItemListWithHeader;
        mainStoryAdapter.setNewData(mainStoryItemListWithHeader);
        mainStoryAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mainStoryAdapter.addFooterView(getFooterView());
        mainStoryAdapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFooter = (TextView) mainStoryAdapter.getFooterLayout().findViewById(R.id.tv_footer);
                tvFooter.setText(getResources().getString(R.string.text_footer_loading));
                getPresenter().fetchBeforeData(tempDate);
            }
        });
    }


    @Override
    public void refresh(String date, TopBanners banners, List<SectionItem> mainStoryItemListWithHeader) {
        tempDate = date;
        setUpBanner(banners);
        if (tempMainStoryItemWithHeader != null) {
            tempMainStoryItemWithHeader.clear();
            tempMainStoryItemWithHeader.addAll(mainStoryItemListWithHeader);
            mainStoryAdapter.notifyDataSetChanged();
        } else {
            initMainStoryView(date, mainStoryItemListWithHeader);
        }
    }

    @Override
    public void showMainStoryContent(String date, TopBanners banners, List<SectionItem> mainStoryItemListWithHeader) {
        setUpBanner(banners);
        hideLoadingView();
        initMainStoryView(date, mainStoryItemListWithHeader);
    }


    public void setUpBanner(final TopBanners banners) {

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                jumpToStoryContent(banners.getIdList().get(position));
            }
        });

        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .setImages(banners.getImages())
                .setBannerTitles(banners.getTitles())
                .setBannerAnimation(Transformer.Default)
                .start();

    }

    public void jumpToStoryContent(String id) {
        Intent intent = new Intent(getActivity(), StoryActivity.class);
        intent.putExtra("story_id", id);
        startActivity(intent);
    }


    @Override
    public void loadMore(String date, List<SectionItem> moreStoryItemListWithHeader) {
        mainStoryAdapter.removeAllFooterView();
        tempDate = date;
        tempMainStoryItemWithHeader.addAll(moreStoryItemListWithHeader);
        mainStoryAdapter.notifyDataSetChanged();
        mainStoryAdapter.loadMoreComplete();
        mainStoryAdapter.addFooterView(getFooterView());
        tvFooter.setText(getResources().getString(R.string.text_footer_get_yesterday_story));
    }

    @Override
    public void dismissRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
            isRefreshing = false;
        }
    }

    @Override
    public void showSuccess() {
    }

    @Override
    public void showFailed() {
        hideLoadingView();
        mainStoryAdapter.setEmptyView(getFailedView());
        showFailedToast();
    }

    @Override
    public void showRefreshFailed() {
        showFailedToast();
    }

    @Override
    public void showLoadMoreFailed() {
        tvFooter.setText(getResources().getString(R.string.text_footer_get_story_list_failed));
        showFailedToast();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void scrollToTop() {
        nestedScrollView.fullScroll(NestedScrollView.SCROLL_INDICATOR_TOP);
    }

    public void showFailedToast() {
        AppUtils.showFloatingAlert(getActivity(), getResources().getString(R.string.text_load_more_failed));
    }

    public View getFailedView() {
        return getActivity().getLayoutInflater().inflate(R.layout.layout_empty_view, (ViewGroup) rvStoryList.getParent(), false);
    }

    public View getFooterView() {
        return getActivity().getLayoutInflater().inflate(R.layout.layout_footer_load_old_content, (ViewGroup) rvStoryList.getParent(), false);
    }

    public void showLoadingView() {
        loadingView.show();
    }

    public void hideLoadingView() {
        loadingView.hide();
    }

}
