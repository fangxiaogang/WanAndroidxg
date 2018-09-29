package com.xiaogang.com.wanandroid_xg.ui.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Articleitem;
import com.xiaogang.com.wanandroid_xg.bean.Banner;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeAdapter;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeContract;
import com.xiaogang.com.wanandroid_xg.ui.home.HomePresenter;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;
import com.xiaogang.com.wanandroid_xg.ui.webcontent.WebcontentFragment;
import com.xiaogang.com.wanandroid_xg.utils.GlideImageLoader;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/17
 */

public class ArticleFragment extends BaseFragment<ArticlePresenter> implements ArticleContract.View,HomeAdapter.RequestLoadMoreListener,HomeAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.mswipeRefreshLayout)
    SwipeRefreshLayout mswipeRefreshLayout;

    @BindView(R.id.homerecycyleview)
    RecyclerView mrecyclerView;

    private int articleId;

    private static final String ARTICLEID = "articleId";

    private ArticleAdapter marticleAdapter;



    private List<Articleitem.DatasBean> marticleitm = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleId = getArguments().getInt(ARTICLEID);
    }

    @Override
    protected void initView(View view) {

        mswipeRefreshLayout.setOnRefreshListener(this);
        mswipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.maincolor));
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mrecyclerView.setAdapter(marticleAdapter = new ArticleAdapter(R.layout.item_article,marticleitm));

        marticleAdapter.setOnItemClickListener(this);


        mPresenter.getArticleItem(articleId);

    }


    @Override
    public void setArticleDate(Articleitem articleitem, int type) {
        if (type == 0) {
            marticleAdapter.setNewData(articleitem.getDatas());
            mswipeRefreshLayout.setRefreshing(false);
            marticleAdapter.loadMoreComplete();
        }else if (type == 1) {
            marticleAdapter.addData(articleitem.getDatas());
            marticleAdapter.loadMoreComplete();
        }
    }

    public static ArticleFragment newInstance(int id) {
        Bundle args = new Bundle();
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        args.putInt(ARTICLEID,id);
        return fragment;
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ((MainFragment) getParentFragment().getParentFragment()).startBrotherFragment(WebcontentFragment.newInstance(marticleAdapter.getItem(position).getLink(),marticleAdapter.getItem(position).getTitle(),marticleAdapter.getItem(position).getId()));
    }



}
