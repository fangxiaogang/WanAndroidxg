package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Knowledge;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/18
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View,knowledgeAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.mswipeRefreshLayout)
    SwipeRefreshLayout mswipeRefreshLayout;

    @BindView(R.id.mrecyclerView)
    RecyclerView mrecyclerView;

    private knowledgeAdapter mknowledgeAdapter;

    private List<Knowledge> knowledges = new ArrayList<>();

    private List<Knowledge> knowledgemsg = new ArrayList<>();

    private List<String> knowledgenames = new ArrayList<>();

    private List<String> knowledgeids = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

        mswipeRefreshLayout.setOnRefreshListener(this);
        mswipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.maincolor));
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mknowledgeAdapter =  new knowledgeAdapter (R.layout.item_knowledge,knowledges);
        mknowledgeAdapter.setOnItemClickListener(this);

        mrecyclerView.setAdapter(mknowledgeAdapter);

        mPresenter.getKnowledge();
    }

    @Override
    public void setknowledgedate(List<Knowledge> knowledges) {
        mknowledgeAdapter.setNewData(knowledges);
        mswipeRefreshLayout.setRefreshing(false);
        mknowledgeAdapter.loadMoreComplete();

        knowledgemsg = knowledges;
    }

    public static KnowledgeFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }




    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onRefresh() {
        mPresenter.getKnowledge();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<String> names = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        Knowledge knowledge =  knowledgemsg.get(position);
        String knowledgename = knowledge.getName();
        for (Knowledge.Children children : knowledge.getChildren()){
            names.add(children.getName());
            ids.add(children.getId());
        }

        ((MainFragment) getParentFragment()).startBrotherFragment(KnowledgeArticleFragment.newInstance(names,ids,knowledgename));
    }
}
