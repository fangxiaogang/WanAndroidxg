package com.xiaogang.com.wanandroid_xg.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Banner;
import com.xiaogang.com.wanandroid_xg.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/17
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.homerecycyleview)
    RecyclerView mrecyclerView;

    private com.youth.banner.Banner mbanner;

    private HomeAdapter mhomeAdapter;

    private View mHomeBannerHeadView;

    private List<Article.DatasBean> marticle = new ArrayList<>();

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

        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mrecyclerView.setAdapter(mhomeAdapter = new HomeAdapter(R.layout.item_home,marticle));

        mHomeBannerHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_banner_head, null);
        mbanner = (com.youth.banner.Banner) mHomeBannerHeadView.findViewById(R.id.banner_ads);
        mhomeAdapter.addHeaderView(mHomeBannerHeadView);

        mPresenter.getBannerdate();
        mPresenter.gethomedate();
    }

    @Override
    public void setBannerdate(List<Banner> bannerers) {
        List<String> images = new ArrayList();
        for(Banner banner : bannerers){
            images.add(banner.getImagePath());
        }
        mbanner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void sethomedate(Article articles) {
        mhomeAdapter.setNewData(articles.getDatas());
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
