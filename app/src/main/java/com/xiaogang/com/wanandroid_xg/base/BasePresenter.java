package com.xiaogang.com.wanandroid_xg.base;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public class BasePresenter <V extends BaseContract.Baseview> implements BaseContract.Basepresenter<V> {


    @Override
    public void attachview(V view) {

    }

    @Override
    public void destroyview() {

    }
}
