package com.xiaogang.com.wanandroid_xg.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.ui.login.LoginFragment;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;
import com.xiaogang.com.wanandroid_xg.ui.mycollect.MyCollectFragment;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.login)
    LinearLayout loginlin;

    @BindView(R.id.out)
    LinearLayout outlin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

        loginlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
            }
        });
        outlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(MyCollectFragment.newInstance());
            }
        });


    }
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
