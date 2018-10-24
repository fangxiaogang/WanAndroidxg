package com.xiaogang.com.wanandroid_xg.ui.project;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Articleitem;
import com.xiaogang.com.wanandroid_xg.bean.Project;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public interface ArticleContract {

    interface View extends BaseContract.Baseview{

        void setArticleDate(Articleitem s, int type);

    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getArticleItem(int id);

        void refresh(int id);

        void loadMore();

    }

}
