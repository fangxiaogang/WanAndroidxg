package com.xiaogang.com.wanandroid_xg.ui.webcontent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/21
 */

public class WebcontentFragment extends BaseFragment<WebcontentPresenter> implements WebcontentContract.View {

    @BindView(R.id.test)
    TextView test;

    @BindView(R.id.mweblin)
    LinearLayout mweblin;

    private String link;

    private static final String URL = "url";

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webcontent;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        link = getArguments().getString(URL);
    }

    @Override
    protected void initView(View view) {
        mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(mweblin, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go(link);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAgentWeb.back();
            }
        });

    }



    public static WebcontentFragment newInstance(String url) {
        WebcontentFragment fragment = new WebcontentFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onBackPressedSupport() {
       if (mAgentWeb.back())  {
           mAgentWeb.back();
       }else {
           return false;
       }
        return true;
    }

}
