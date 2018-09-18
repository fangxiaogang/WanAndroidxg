package com.xiaogang.com.wanandroid_xg.ui.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.bean.Article;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/18
 */

public class HomeAdapter extends BaseQuickAdapter<Article.DatasBean,BaseViewHolder>{


    public HomeAdapter(int layoutResId, @Nullable List<Article.DatasBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Article.DatasBean item) {
        helper.setText(R.id.tvAuthor, item.getAuthor());
        helper.setText(R.id.tvNiceDate, item.getNiceDate());
        helper.setText(R.id.tvTitle, Html.fromHtml(item.getTitle()));
        helper.setText(R.id.tvChapterName, item.getChapterName());
        helper.addOnClickListener(R.id.tvChapterName);
        helper.addOnClickListener(R.id.ivCollect);
    }
}
