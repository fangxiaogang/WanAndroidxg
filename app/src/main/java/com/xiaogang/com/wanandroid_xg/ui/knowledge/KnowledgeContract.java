package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.Banner;
import com.xiaogang.com.wanandroid_xg.bean.Knowledge;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/18
 */

public interface KnowledgeContract {

    interface View extends BaseContract.Baseview {
        void setknowledgedate (List<Knowledge> knowledges);
    }

    interface Presenter extends BaseContract.Basepresenter<View> {

        void getKnowledge();

    }

}
