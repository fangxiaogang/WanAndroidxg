package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/29
 */

public class KnowledgeArticleFragment extends BaseFragment{

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewpager)
    ViewPager mViewpager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledgeArticle;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }

}
