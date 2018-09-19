package com.xiaogang.com.wanandroid_xg.ui.project;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Project;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public interface ProjectContract {

    interface View extends BaseContract.Baseview{

        void setProjectDate (List<Project> projects);

    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getProjectDate();

    }

}
