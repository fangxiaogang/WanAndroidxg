package com.xiaogang.com.wanandroid_xg.base;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public class BasePresenter <T extends BaseContract.Baseview> implements BaseContract.Basepresenter<T> {

    protected T mView;

    @Override
    public void attachview(T view) {
        this.mView = view;
    }

    @Override
    public void destroyview() {
        if (mView != null ) {
            mView = null;
        }
    }
}
