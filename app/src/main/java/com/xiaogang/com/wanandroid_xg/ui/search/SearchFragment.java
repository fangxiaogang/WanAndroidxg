package com.xiaogang.com.wanandroid_xg.ui.search;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeAdapter;
import com.xiaogang.com.wanandroid_xg.ui.webcontent.WebcontentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/10/8
 */

public class SearchFragment extends BaseFragment<SearchPresenter> implements SearchContract.View,HomeAdapter.RequestLoadMoreListener,HomeAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.mswipeRefreshLayout)
    SwipeRefreshLayout mswipeRefreshLayout;

    @BindView(R.id.searchrecycyleview)
    RecyclerView msearchrecycyleview;

    private HomeAdapter mhomeAdapter;

    private List<Article.DatasBean> marticle = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

        mswipeRefreshLayout.setOnRefreshListener(this);
        mswipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.maincolor));
        msearchrecycyleview.setLayoutManager(new LinearLayoutManager(getContext()));
        msearchrecycyleview.setAdapter(mhomeAdapter = new HomeAdapter(R.layout.item_home,marticle));
        mhomeAdapter.setOnLoadMoreListener(this);

        mPresenter.getSearchdate("面试");
    }

    @Override
    public void setSearchdate(Article articles, int type) {
        if (type == 0) {
            mhomeAdapter.setNewData(articles.getDatas());
            mswipeRefreshLayout.setRefreshing(false);
            mhomeAdapter.loadMoreComplete();
        }else if (type == 1) {
            mhomeAdapter.addData(articles.getDatas());
            if (articles.getDatas() == null || articles.getDatas().size() < 20) {
                mhomeAdapter.loadMoreEnd(false);
            }else {
                mhomeAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        start(WebcontentFragment.newInstance(mhomeAdapter.getItem(position).getLink(),mhomeAdapter.getItem(position).getTitle(),mhomeAdapter.getItem(position).getId()));
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
