package com.xiaogang.com.wanandroid_xg.ui.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Banner;

import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/17
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.homerecycyleview)
    RecyclerView mrecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mPresenter.getBannerdate();
    }

    @Override
    public void setBannerdate(List<Banner> bannerers) {
        System.out.println(bannerers.hashCode());
    }

    @Override
    public void sethomedate(Article articles) {

    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
