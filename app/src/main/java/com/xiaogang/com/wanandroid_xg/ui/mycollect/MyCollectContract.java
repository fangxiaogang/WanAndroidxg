package com.xiaogang.com.wanandroid_xg.ui.mycollect;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Article;

/**
 * author: fangxiaogang
 * date: 2018/9/10
 */

public interface MyCollectContract {

    interface View extends BaseContract.Baseview {

        void getMyCollectSuccess (Article article,int type);

    }

    interface Presenter extends BaseContract.Basepresenter<MyCollectContract.View>{

        void getMyCollects ();

        void refresh();

        void loadMore();

    };

}
