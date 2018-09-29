package com.xiaogang.com.wanandroid_xg.ui.project;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.bean.Articleitem;
import com.xiaogang.com.wanandroid_xg.utils.GlideImageLoader;

import java.util.List;

/**
 * Created by xiaogang on 2018/9/29.
 */

public class ArticleAdapter extends BaseQuickAdapter<Articleitem.DatasBean,BaseViewHolder> {


    public ArticleAdapter(int layoutResId, @Nullable List<Articleitem.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Articleitem.DatasBean item) {
        helper.setText(R.id.tvTitle,item.getTitle());
        helper.setText(R.id.tvContent,item.getDesc());
        helper.setText(R.id.tvAuthor, item.getAuthor());
        helper.setText(R.id.tvNiceDate, item.getNiceDate());
        ImageView articleiv = helper.getView(R.id.articleiv);
        Glide.with(mContext).load(item.getEnvelopePic()).into(articleiv);

    }
}
