package com.xiaogang.com.wanandroid_xg.ui.mycollect;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeAdapter;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;
import com.xiaogang.com.wanandroid_xg.ui.webcontent.WebcontentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class MyCollectFragment extends BaseFragment<MyCollectPresenter> implements MyCollectContract.View,HomeAdapter.RequestLoadMoreListener,HomeAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.backtitle)
    ImageView backtitle;

    @BindView(R.id.mswipeRefreshLayout)
    SwipeRefreshLayout mswipeRefreshLayout;

    @BindView(R.id.colletrecycyleview)
    RecyclerView mcolletrecycyleview;

    private HomeAdapter mhomeAdapter;

    private List<Article.DatasBean> marticle = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mswipeRefreshLayout.setOnRefreshListener(this);
        mswipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.maincolor));
        mcolletrecycyleview.setLayoutManager(new LinearLayoutManager(getContext()));
        mcolletrecycyleview.setAdapter(mhomeAdapter = new HomeAdapter(R.layout.item_home,marticle));
        mhomeAdapter.setOnLoadMoreListener(this);
        mhomeAdapter.setOnItemClickListener(this);

        mPresenter.getMyCollects();


        backtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void getMyCollectSuccess(Article article,int type) {
        if (type == 0) {
            mhomeAdapter.setNewData(article.getDatas());
            mswipeRefreshLayout.setRefreshing(false);
            mhomeAdapter.loadMoreComplete();
        }else if (type == 1) {
            mhomeAdapter.addData(article.getDatas());
            if (article.getDatas() == null || article.getDatas().size() < 20) {
                mhomeAdapter.loadMoreEnd(false);
            }else {
                mhomeAdapter.loadMoreComplete();
            }

        }


    }

    public static MyCollectFragment newInstance() {
        Bundle args = new Bundle();
        MyCollectFragment fragment = new MyCollectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        start(WebcontentFragment.newInstance(mhomeAdapter.getItem(position).getLink(),mhomeAdapter.getItem(position).getTitle()));
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }


}
