package com.shineyang.zhidaily.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.Column;

import java.util.List;

/**
 * Created by ShineYang on 2017/4/18.
 */

public class ColumnContentAdapter extends BaseQuickAdapter<Column.ColumnContent, BaseViewHolder> {
    public ColumnContentAdapter(int layoutResId, List<Column.ColumnContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Column.ColumnContent item) {
        helper.setText(R.id.tv_column_content_name, item.getTitle());
        if (item.getImages() != null) {
            Glide.with(mContext)
                    .load(item.getImages().get(0)).crossFade()
                    .into((ImageView) helper.getView(R.id.iv_column_content_image));
        } else {
            helper.getView(R.id.iv_column_content_image).setVisibility(View.GONE);
        }
    }

}
