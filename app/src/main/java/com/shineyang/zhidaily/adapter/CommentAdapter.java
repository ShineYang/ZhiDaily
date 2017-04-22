package com.shineyang.zhidaily.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.bean.Comment;
import com.shineyang.zhidaily.utils.AppUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by ShineYang on 2017/4/7.
 */

public class CommentAdapter extends BaseQuickAdapter<Comment.CommentContent, BaseViewHolder> {

    public CommentAdapter(int layoutResId, List<Comment.CommentContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment.CommentContent item) {

        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_comment_content, item.getContent());
        if (item.getReplyTo() != null) {
            helper.setText(R.id.tv_reply_to, "//回复@"
                    + item.getReplyTo().getReplyToAuthor()
                    + ": "
                    + item.getReplyTo().getReplyToContent());
        } else {
            helper.getView(R.id.tv_reply_to).setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_comment_date, AppUtils.formatDate(1, item.getTime()));
        helper.setText(R.id.tv_like_number, item.getLikes());

        Glide.with(mContext).load(item.getAvatar())
                .placeholder(R.drawable.ic_user)
                .bitmapTransform(new CropCircleTransformation(mContext))
                .into((ImageView) helper.getView(R.id.iv_user_avatar));

    }

    public void setReplyTextStyle() {
        //TODO
    }
}
