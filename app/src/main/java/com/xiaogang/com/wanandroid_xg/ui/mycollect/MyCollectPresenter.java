package com.xiaogang.com.wanandroid_xg.ui.mycollect;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.bean.MyCollect;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author: fangxiaogang
 * date: 2018/9/10
 */

public class MyCollectPresenter extends BasePresenter<MyCollectContract.View> implements MyCollectContract.Presenter {

    @Inject
    public MyCollectPresenter (){

    }


    @Override
    public void getMyCollects() {
        RetrofitManager.create(ApiServer.class)
                .getMyCollect(0)
                .compose(RxSchedulers.<DataResponse<MyCollect>>applySchedulers())
                .compose(mView.<DataResponse<MyCollect>>bindToLife())
                .subscribe(new Consumer<DataResponse<MyCollect>>() {
                    @Override
                    public void accept(DataResponse<MyCollect> myCollectDataResponse) throws Exception {
                        mView.getMyCollectSuccess(myCollectDataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }



}
