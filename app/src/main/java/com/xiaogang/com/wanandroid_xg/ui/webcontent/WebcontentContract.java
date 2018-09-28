package com.xiaogang.com.wanandroid_xg.ui.webcontent;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;

/**
 * author: fangxiaogang
 * date: 2018/9/21
 */

public interface WebcontentContract  {

    interface View extends BaseContract.Baseview {

        void addCollectSuccess(String msg);

    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void addCollect(int id);

    }

}
