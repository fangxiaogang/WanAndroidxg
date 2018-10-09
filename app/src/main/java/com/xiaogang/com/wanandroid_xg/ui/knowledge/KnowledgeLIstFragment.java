package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.ui.login.LoginFragment;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;
import com.xiaogang.com.wanandroid_xg.ui.webcontent.WebcontentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/29
 */

public class KnowledgeLIstFragment extends BaseFragment<KnowledgeLIstPresenter> implements KnowledgeLIstContract.View,SwipeRefreshLayout.OnRefreshListener,KnowledgeLIstAdapter.OnItemClickListener,KnowledgeLIstAdapter.RequestLoadMoreListener {

    @BindView(R.id.mswipeRefreshLayout)
    SwipeRefreshLayout mswipeRefreshLayout;

    @BindView(R.id.mrecyclerView)
    RecyclerView mrecyclerView;

    private static final String ID = "id";

    private int id ;

    private KnowledgeLIstAdapter mknowledgeLIstAdapter;

    private List<Article.DatasBean> articles = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledgelist;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(ID);
    }

    @Override
    protected void initView(View view) {

        mswipeRefreshLayout.setOnRefreshListener(this);
        mswipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.maincolor));
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mknowledgeLIstAdapter =  new KnowledgeLIstAdapter (R.layout.item_home,articles);
        mknowledgeLIstAdapter.setOnItemClickListener(this);

        mrecyclerView.setAdapter(mknowledgeLIstAdapter);

        mPresenter.getKnowledgeLIst(id);

    }

    @Override
    public void setKnowledgeLIst(Article article) {
        mknowledgeLIstAdapter.setNewData(article.getDatas());
    }




    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ((KnowledgeArticleFragment)getParentFragment()).startBrotherFragment(WebcontentFragment.newInstance(mknowledgeLIstAdapter.getItem(position).getLink(),mknowledgeLIstAdapter.getItem(position).getTitle(),mknowledgeLIstAdapter.getItem(position).getId(),mknowledgeLIstAdapter.getItem(position).isCollect()));
    }

    @Override
    public void onLoadMoreRequested() {

    }

    public static KnowledgeLIstFragment newInstance(int id) {
        Bundle args = new Bundle();
        KnowledgeLIstFragment fragment = new KnowledgeLIstFragment();
        args.putInt(ID,id);
        fragment.setArguments(args);
        return fragment;
    }
}
