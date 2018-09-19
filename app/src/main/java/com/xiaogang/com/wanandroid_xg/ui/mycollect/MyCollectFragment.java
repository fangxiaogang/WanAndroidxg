package com.xiaogang.com.wanandroid_xg.ui.mycollect;

import android.view.View;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.MyCollect;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class MyCollectFragment extends BaseFragment<MyCollectPresenter> implements MyCollectContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void getMyCollectSuccess(MyCollect myCollect) {

    }
}
