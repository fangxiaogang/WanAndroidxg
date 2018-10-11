package com.xiaogang.com.wanandroid_xg.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/10/11
 */

public class AboutFragment extends BaseFragment {

    @BindView(R.id.backtitle)
    ImageView backtitle;

    @BindView(R.id.starlin)
    LinearLayout mstarlin;

    @BindView(R.id.upgradelin)
    LinearLayout mupgradelin;

    @BindView(R.id.githunlin)
    LinearLayout mgithunlin;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

        backtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        mupgradelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("To do");
            }
        });

        mstarlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/fangxiaogang/WanAndroidxg"));
                startActivity(intent);
            }
        });

        mgithunlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/fangxiaogang"));
                startActivity(intent);
            }
        });


    }

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
