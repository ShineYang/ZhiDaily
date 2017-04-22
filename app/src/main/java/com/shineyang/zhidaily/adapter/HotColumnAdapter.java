package com.shineyang.zhidaily.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.HotColumn;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by ShineYang on 2017/4/20.
 */

public class HotColumnAdapter extends BaseQuickAdapter<HotColumn.HotColumnStory, BaseViewHolder> {
    public HotColumnAdapter(int layoutResId, List<HotColumn.HotColumnStory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotColumn.HotColumnStory item) {
        helper.setText(R.id.tv_hot_column_item_title, "#" + item.getDisplayDate());
        Glide.with(mContext).load(item.getImages()[0]).crossFade()
                .into((ImageView) helper.getView(R.id.iv_hot_column_item_image));
    }
}
