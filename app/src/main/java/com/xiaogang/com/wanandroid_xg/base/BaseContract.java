package com.xiaogang.com.wanandroid_xg.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public interface BaseContract {

    interface Basepresenter <V extends BaseContract.Baseview>{

        void attachview (V view);

        void destroyview ();
    }

    interface Baseview {

        //显示进度中
        void showLoading();

        //隐藏进度
        void hideLoading();

        //显示请求成功
        void showSuccess(String message);

        //失败重试
        void showFaild(String message);

        //绑定生命周期
        <V> LifecycleTransformer<V> bindToLife();
    }

}
