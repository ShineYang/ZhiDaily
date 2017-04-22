package com.shineyang.zhidaily.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.OpenSourceProject;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/21.
 */

public class OpenSourceProjectsAdapter extends BaseQuickAdapter<OpenSourceProject.OpenSourceProjectContent, BaseViewHolder> {
    public OpenSourceProjectsAdapter(int layoutResId, List<OpenSourceProject.OpenSourceProjectContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenSourceProject.OpenSourceProjectContent item) {
        helper.setText(R.id.tv_open_source_project_name, item.getProjectName());
        helper.setText(R.id.tv_open_source_project_license, item.getProjectLicense());
        helper.setText(R.id.tv_open_source_project_author, item.getProjectAuthor());
        helper.setText(R.id.tv_open_source_project_url, item.getProjectUrl());

    }
}
