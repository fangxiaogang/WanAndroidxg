package com.xiaogang.com.wanandroid_xg;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;

/**
 * author: fangxiaogang
 * date: 2018/10/9
 */

public class FirstFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startWithPop(MainFragment.newInstance());
            }
        },800);
    }

    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
