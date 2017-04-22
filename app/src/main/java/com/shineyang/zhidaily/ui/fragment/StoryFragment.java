package com.shineyang.zhidaily.ui.fragment;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.StoryContent;
import com.shineyang.zhidaily.iVew.IStoryView;
import com.shineyang.zhidaily.presenter.StoryPresenter;
import com.shineyang.zhidaily.utils.AppUtils;
import com.shineyang.zhidaily.utils.SharedPreferencesUtils;
import com.shineyang.zhidaily.utils.WebUtils;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ShineYang on 2017/2/5.
 */

public class StoryFragment extends MvpFragment<IStoryView, StoryPresenter> implements IStoryView {
    public static final String TAG = StoryFragment.class.getSimpleName();

    @BindView(R.id.webViewContainer)
    LinearLayout llWebViewContainer;
    @BindView(R.id.sv_webView)
    ScrollView sv_webView;
    @BindView(R.id.story_header_image)
    ImageView story_header_image;
    @BindView(R.id.tv_story_title)
    TextView tvStoryTitle;
    @BindView(R.id.tv_pic_copyright)
    TextView tvPicCopyRight;
    @BindView(R.id.content_loading_view)
    AVLoadingIndicatorView content_loading_view;
    @BindView(R.id.webview)
    WebView webView;

    private StoryContent storyContent;
    private String storyId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            storyId = getArguments().getString("story_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_story, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        sv_webView.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().getStoryContent(storyId);
        showLoadingView();
    }

    public static StoryFragment newInstance(String storyId) {
        StoryFragment fragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("story_id", storyId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public StoryPresenter createPresenter() {
        return new StoryPresenter();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void bindWebView(StoryContent storyContent) {
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        if (TextUtils.isEmpty(storyContent.getBody())) {

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();  // 接受所有网站的证书
                }
            });

            webView.loadUrl(storyContent.getShareUrl());

        } else {
            SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(getActivity());
            Boolean isNightMode = sharedPreferencesUtils.getBoolean("is_night_mode");
            String data = WebUtils.buildHtmlWithCss(storyContent.getBody(), storyContent.getCssList(), isNightMode);
            webView.loadDataWithBaseURL(WebUtils.BASE_URL, data, WebUtils.MIME_TYPE, WebUtils.ENCODING, null);
        }
    }

    private void loadHeaderInfo() {
        if (!TextUtils.isEmpty(storyContent.getImage())) {
            Glide.with(getActivity())
                    .load(storyContent.getImage()).centerCrop()
                    .into(story_header_image);
        } else {
            story_header_image.setImageDrawable(getResources().getDrawable(R.drawable.header_image_place_holder));
        }
        tvStoryTitle.setText(storyContent.getTitle());
        if (!TextUtils.isEmpty(storyContent.getImageSource())) {
            tvPicCopyRight.setText(getResources().getString(R.string.text_pic_copyright) + storyContent.getImageSource());
        } else {
            tvPicCopyRight.setText(getResources().getString(R.string.text_pic_from_internet));
        }

    }

    @Override
    public void showStoryView(final StoryContent content) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (content != null) {
                    hideLoadingView();
                    storyContent = content;
                    bindWebView(storyContent);
                    loadHeaderInfo();
                }
            }
        };
        getActivity().runOnUiThread(runnable);

    }

    @Override
    public void showError() {
        AppUtils.showFloatingAlert(getActivity(), getResources().getString(R.string.text_get_story_content_failed));
        hideLoadingView();
    }

    public void showLoadingView() {
        content_loading_view.show();
    }

    public void hideLoadingView() {
        content_loading_view.hide();
    }

}
