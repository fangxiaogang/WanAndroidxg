package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Knowledge;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/18
 */

public class knowledgeAdapter extends BaseQuickAdapter<Knowledge,BaseViewHolder>{


    public knowledgeAdapter(int layoutResId, @Nullable List<Knowledge> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Knowledge item) {
        helper.setText(R.id.tvTitle, item.getName());
        StringBuffer childrenname = new StringBuffer();
        for (Knowledge.Children children : item.getChildren()) {
            childrenname.append(children.getName() + "      ");
        }
        helper.setText(R.id.tvContent, childrenname.toString());
        
    }
}
