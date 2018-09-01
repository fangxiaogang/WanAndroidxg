package com.xiaogang.com.wanandroid_xg.base;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Unbinder;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public abstract class BaseActivity <V extends BaseContract.Basepresenter> extends RxAppCompatActivity implements BaseContract.Baseview{

    private Unbinder unbinder;

    protected abstract int getLayoutid();

    protected abstract void initView();

}
