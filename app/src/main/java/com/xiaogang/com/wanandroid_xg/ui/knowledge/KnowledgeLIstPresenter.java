package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author: fangxiaogang
 * date: 2018/9/29
 */

public class KnowledgeLIstPresenter extends BasePresenter<KnowledgeLIstContract.View> implements KnowledgeLIstContract.Presenter {

    private int mPage = 0;

    private boolean isRefresh = true;


    @Inject
    public KnowledgeLIstPresenter() {

    }

    @Override
    public void getKnowledgeLIst(int id) {
        RetrofitManager.create(ApiServer.class)
                .getKnowledgelist(mPage,id)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> articleDataResponse) throws Exception {
                        mView.setKnowledgeLIst(articleDataResponse.getData());
                    }
                });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }
}
