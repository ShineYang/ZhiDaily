package com.shineyang.zhidaily.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.SettingAdapter;
import com.shineyang.zhidaily.bean.SettingItem;
import com.shineyang.zhidaily.iVew.IMoreView;
import com.shineyang.zhidaily.presenter.MorePresenter;
import com.shineyang.zhidaily.ui.activity.OpenSourceListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ShineYang on 2017/1/15.
 */

public class FragmentMore extends MvpFragment<IMoreView, MorePresenter> implements IMoreView {
    @BindView(R.id.rv_settings)
    RecyclerView rvSettings;

    private static final String MAIL_TO_URI = "mailto:realshineyang@gmail.com";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_more, container, false);
    }

    @NonNull
    @Override
    public MorePresenter createPresenter() {
        return new MorePresenter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getPresenter().setSettingItemData();
    }

    public void initRVSettings(List<SettingItem> settingItems) {
        SettingAdapter settingAdapter = new SettingAdapter(R.layout.layout_setting_item, settingItems);
        settingAdapter.setOnSwitchClickListener(new SettingAdapter.OnSwitchClickListener() {
            @Override
            public void onSwitchClick(Boolean isNightMode) {
                getActivity().recreate();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);
        rvSettings.setLayoutManager(layoutManager);
        rvSettings.setAdapter(settingAdapter);
        rvSettings.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 1:
                        Intent sendIntent =new Intent(Intent.ACTION_SENDTO);
                        sendIntent.setData(Uri.parse(MAIL_TO_URI));
                        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.text_feedback));
                        startActivity(sendIntent);
                        break;
                    case 2:
                        Intent intent = new Intent(getActivity(), OpenSourceListActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        new MaterialDialog.Builder(getActivity())
                                .title(getResources().getString(R.string.text_title_about))
                                .content(getResources().getString(R.string.text_about_content))
                                .positiveText(getResources().getString(R.string.text_positive))
                                .show();
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public void setSettingData(List<SettingItem> settingItems) {
        initRVSettings(settingItems);
    }

}
