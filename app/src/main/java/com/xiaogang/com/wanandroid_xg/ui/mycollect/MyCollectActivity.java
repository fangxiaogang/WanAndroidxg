package com.xiaogang.com.wanandroid_xg.ui.mycollect;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseActivity;
import com.xiaogang.com.wanandroid_xg.bean.MyCollect;

/**
 * author: fangxiaogang
 * date: 2018/9/10
 */

public class MyCollectActivity extends BaseActivity<MyCollectPresenter> implements MyCollectContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        mPresenter.getMyCollects();
    }

    @Override
    protected void initInjector() {
            mActivityComponent.inject(this);
    }

    @Override
    public void getMyCollectSuccess(MyCollect myCollect) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showFaild(String message) {

    }


}
