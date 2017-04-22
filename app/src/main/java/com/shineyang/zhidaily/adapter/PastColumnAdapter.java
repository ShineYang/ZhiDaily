package com.shineyang.zhidaily.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.PastColumns;

import java.util.List;


/**
 * Created by ShineYang on 2017/4/11.
 */

public class PastColumnAdapter extends BaseQuickAdapter<PastColumns.PastColumnContent, BaseViewHolder> {
    public PastColumnAdapter(int layoutResId, List<PastColumns.PastColumnContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PastColumns.PastColumnContent item) {
        helper.setText(R.id.tv_past_column_name, item.getName());


    }
}
