package com.xiaogang.com.wanandroid_xg.ui.home;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Banner;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author: fangxiaogang
 * date: 2018/9/17
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private int mPage;

    private boolean isRefresh = true;

    @Inject
    public HomePresenter() {

    }

    @Override
    public void getBannerdate() {
        RetrofitManager.create(ApiServer.class)
                .getHomeBanners()
                .compose(RxSchedulers.<DataResponse<List<Banner>>>applySchedulers())
                .compose(mView.<DataResponse<List<Banner>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<Banner>>>() {
                    @Override
                    public void accept(DataResponse<List<Banner>> listDataResponse) throws Exception {
                        mView.setBannerdate(listDataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    @Override
    public void gethomedate() {
        RetrofitManager.create(ApiServer.class)
                .getHomeArticles(mPage)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> articleDataResponse) throws Exception {
                        if (isRefresh) {
                            mView.sethomedate(articleDataResponse.getData(),0);
                        }else {
                            mView.sethomedate(articleDataResponse.getData(),1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }



    @Override
    public void refresh() {
        isRefresh = true;
        getBannerdate();
        gethomedate();
    }

    @Override
    public void loadMore() {
        mPage ++;
        isRefresh = false;
        gethomedate();
    }


}
