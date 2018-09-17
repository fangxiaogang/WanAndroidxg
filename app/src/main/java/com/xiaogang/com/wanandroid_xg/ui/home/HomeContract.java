package com.xiaogang.com.wanandroid_xg.ui.home;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Banner;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/17
 */

public interface HomeContract {

    interface View extends BaseContract.Baseview {

        void setBannerdate (List<Banner> bannerers);

        void sethomedate (Article articles);
    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getBannerdate();

        void gethomedate();

        void refresh();

        void loadMore();
    }


}
