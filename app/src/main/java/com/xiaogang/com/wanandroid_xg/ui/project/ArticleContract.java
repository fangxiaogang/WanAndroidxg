package com.xiaogang.com.wanandroid_xg.ui.project;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Project;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public interface ArticleContract {

    interface View extends BaseContract.Baseview{

        void setArticleDate(Article articles, int type);

    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getArticle();

        void refresh();

        void loadMore();

    }

}
