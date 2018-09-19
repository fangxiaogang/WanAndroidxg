package com.xiaogang.com.wanandroid_xg.ui.project;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
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

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    @Inject
    public ProjectPresenter() {

    }

    @Override
    public void getProjectDate() {
        RetrofitManager.create(ApiServer.class)
                .getProject()
                .compose(RxSchedulers.<DataResponse<List<Project>>>applySchedulers())
                .compose(mView.<DataResponse<List<Project>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<Project>>>() {
                    @Override
                    public void accept(DataResponse<List<Project>> listDataResponse) throws Exception {
                        mView.setProjectDate(listDataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }


}
