package com.xiaogang.com.wanandroid_xg.ui.search;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Article;

/**
 * author: fangxiaogang
 * date: 2018/10/8
 */

public interface SearchContract {

    interface View extends BaseContract.Baseview{

        void setSearchdate(Article articles, int type);

    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getSearchdate(String k);

        void refresh();

        void loadMore();

    }

}
