package com.xiaogang.com.wanandroid_xg.ui.mine;

import android.os.Bundle;
import android.view.View;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class MineFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
