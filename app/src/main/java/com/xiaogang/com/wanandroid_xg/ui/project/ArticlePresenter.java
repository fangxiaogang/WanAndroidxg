package com.xiaogang.com.wanandroid_xg.ui.project;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.Articleitem;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.bean.Project;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class ArticlePresenter extends BasePresenter<ArticleContract.View> implements ArticleContract.Presenter {

    private int mpage = 1;

    private boolean isRefresh = true;

    @Inject
    public ArticlePresenter() {

    }

    @Override
    public void getArticleItem(int id) {
        RetrofitManager.create(ApiServer.class)
                .getArticleItem(mpage,id)
                .compose(RxSchedulers.<DataResponse<Articleitem>>applySchedulers())
                .compose(mView.<DataResponse<Articleitem>>bindToLife())
                .subscribe(new Consumer<DataResponse<Articleitem>>() {
                    @Override
                    public void accept(DataResponse<Articleitem> articleitemDataResponse) throws Exception {
                        if (isRefresh) {
                            mView.setArticleDate(articleitemDataResponse.getData(),0);
                        }else {
                            mView.setArticleDate(articleitemDataResponse.getData(),1);
                        }

                    }
                });
    }

    @Override
    public void refresh(int id) {
        RetrofitManager.create(ApiServer.class)
                .getArticleItem(mpage,id)
                .compose(RxSchedulers.<DataResponse<Articleitem>>applySchedulers())
                .compose(mView.<DataResponse<Articleitem>>bindToLife())
                .subscribe(new Consumer<DataResponse<Articleitem>>() {
                    @Override
                    public void accept(DataResponse<Articleitem> articleitemDataResponse) throws Exception {
                        if (isRefresh) {
                            mView.setArticleDate(articleitemDataResponse.getData(),0);
                        }else {
                            mView.setArticleDate(articleitemDataResponse.getData(),1);
                        }

                    }
                });
    }

    @Override
    public void loadMore() {

    }
}
