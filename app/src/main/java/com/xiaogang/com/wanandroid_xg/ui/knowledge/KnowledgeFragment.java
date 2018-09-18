package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Knowledge;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/18
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

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
        mPresenter.getKnowledge();
    }

    @Override
    public void setknowledgedate(List<Knowledge> knowledges) {
        System.out.println(knowledges.get(0).getName());
        ToastUtils.showShort(knowledges.get(0).getName());
    }

    public static KnowledgeFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
