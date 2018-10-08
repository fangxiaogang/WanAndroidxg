package com.xiaogang.com.wanandroid_xg.ui.search;

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
 * date: 2018/10/8
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter{

    private int mpage = 1;

    private boolean isRefresh = true;

    @Inject
    public SearchPresenter() {

    }


    @Override
    public void getSearchdate(String k) {
        RetrofitManager.create(ApiServer.class)
                .getSearchArticles(mpage,k)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> articleDataResponse) throws Exception {
                        if (isRefresh) {
                            mView.setSearchdate(articleDataResponse.getData(),0);
                        }else {
                            mView.setSearchdate(articleDataResponse.getData(),1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
