package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.bean.Article;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/30
 */

public class KnowledgeLIstAdapter extends BaseQuickAdapter<Article.DatasBean,BaseViewHolder> {

    public KnowledgeLIstAdapter(int layoutResId, @Nullable List<Article.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DatasBean item) {
        helper.setText(R.id.tvAuthor, item.getAuthor());
        helper.setText(R.id.tvNiceDate, item.getNiceDate());
        helper.setText(R.id.tvTitle, item.getTitle());
        TextView textView = helper.getView(R.id.tvContent);
        if (!StringUtils.isEmpty(item.getDesc())){
            helper.setText(R.id.tvContent, item.getDesc());
            textView.setVisibility(View.VISIBLE);
        }else {
            textView.setVisibility(View.GONE);
        }
        if (StringUtils.isEmpty(item.getsuperChapterName())) {
            helper.setText(R.id.tvChapterName, item.getChapterName());
        }else {
            helper.setText(R.id.tvChapterName,  item.getsuperChapterName() + " / "+ item.getChapterName());
        }
    }
}
