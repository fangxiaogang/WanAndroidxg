package com.xiaogang.com.wanandroid_xg.ui.project;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public interface ProjectContract {

    interface View extends BaseContract.Baseview{

        void setProjectDate ();

    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getProjectDate();

    }

}
