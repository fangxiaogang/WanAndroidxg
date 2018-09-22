package com.xiaogang.com.wanandroid_xg.ui.webcontent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

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

    }



    @Override
    protected void initView(View view) {
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(WebcontentFragment.newInstance("www."));
            }
        });
    }

    public static WebcontentFragment newInstance(String url) {
        WebcontentFragment fragment = new WebcontentFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

}
