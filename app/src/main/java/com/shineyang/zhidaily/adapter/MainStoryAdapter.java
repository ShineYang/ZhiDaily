package com.shineyang.zhidaily.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.adapter.item.MainStoryItem;
import com.shineyang.zhidaily.adapter.item.SectionItem;
import com.shineyang.zhidaily.utils.AppUtils;

import java.util.List;

/**
 * Created by ShineYang on 2017/1/16.
 */

public class MainStoryAdapter extends BaseSectionQuickAdapter<SectionItem, BaseViewHolder> {
    public MainStoryAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionItem item) {
        helper.setText(R.id.tv_head_date, AppUtils.formatDate(0, item.header));
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionItem item) {

        MainStoryItem mainStoryItem = (MainStoryItem) item.t;

        helper.setText(R.id.tv_rv_story_title, mainStoryItem.getTitle());
        Glide.with(mContext)
                .load(mainStoryItem.getImageResource()).crossFade()
                .placeholder(R.drawable.rv_image_placeholder)
                .into((ImageView) helper.getView(R.id.iv_rv_story_image));
    }
}
