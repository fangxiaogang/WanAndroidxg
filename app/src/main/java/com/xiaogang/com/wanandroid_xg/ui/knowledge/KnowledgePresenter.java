package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.bean.Knowledge;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author: fangxiaogang
 * date: 2018/9/18
 */

public class KnowledgePresenter extends BasePresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {

    @Inject
    public KnowledgePresenter() {

    }

    @Override
    public void getKnowledge() {
        RetrofitManager.create(ApiServer.class)
                .getKnowledge()
                .compose(RxSchedulers.<DataResponse<List<Knowledge>>>applySchedulers())
                .compose(mView.<DataResponse<List<Knowledge>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<Knowledge>>>() {
                    @Override
                    public void accept(DataResponse<List<Knowledge>> dataResponse) throws Exception {
                        mView.setknowledgedate(dataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
