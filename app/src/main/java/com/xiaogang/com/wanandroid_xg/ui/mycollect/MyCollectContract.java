package com.xiaogang.com.wanandroid_xg.ui.mycollect;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.MyCollect;

/**
 * author: fangxiaogang
 * date: 2018/9/10
 */

public interface MyCollectContract {

    interface View extends BaseContract.Baseview {

        void getMyCollectSuccess (MyCollect myCollect);

    }

    interface Presenter extends BaseContract.Basepresenter<MyCollectContract.View>{

        void getMyCollects ();

    };

}
