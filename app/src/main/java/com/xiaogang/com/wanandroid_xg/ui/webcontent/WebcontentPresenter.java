package com.xiaogang.com.wanandroid_xg.ui.webcontent;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author: fangxiaogang
 * date: 2018/9/21
 */

public class WebcontentPresenter extends BasePresenter<WebcontentContract.View> implements WebcontentContract.Presenter {

    @Inject
    public WebcontentPresenter(){

    }

    @Override
    public void addCollect(int id) {
        RetrofitManager.create(ApiServer.class)
                .addCollect(id)
                .compose(RxSchedulers.<DataResponse>applySchedulers())
                .compose(mView.<DataResponse>bindToLife())
                .subscribe(new Consumer<DataResponse>() {
                    @Override
                    public void accept(DataResponse dataResponse) throws Exception {
                        if (dataResponse.getErrorCode() == 0) {
                            mView.addCollectSuccess("已收藏");
                        }else {
                            mView.addCollectSuccess("收藏失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void removeCollect(int id) {
        RetrofitManager.create(ApiServer.class)
                .removeCollect(id,-1)
                .compose(RxSchedulers.<DataResponse>applySchedulers())
                .compose(mView.<DataResponse>bindToLife())
                .subscribe(new Consumer<DataResponse>() {
                    @Override
                    public void accept(DataResponse dataResponse) throws Exception {
                        if (dataResponse.getErrorCode() == 0) {
                            mView.removeCollectSuccess("取消收藏成功");
                        }else {
                            mView.removeCollectSuccess("取消收藏失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
