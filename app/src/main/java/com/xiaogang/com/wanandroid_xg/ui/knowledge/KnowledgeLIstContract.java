package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Article;

/**
 * author: fangxiaogang
 * date: 2018/9/29
 */

public interface  KnowledgeLIstContract {

    interface View extends BaseContract.Baseview {

        void setKnowledgeLIst(Article article);

    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getKnowledgeLIst(int id);

        void refresh();

        void loadMore();

    }

}
