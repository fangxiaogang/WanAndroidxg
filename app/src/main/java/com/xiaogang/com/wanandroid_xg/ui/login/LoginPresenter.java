package com.xiaogang.com.wanandroid_xg.ui.login;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.bean.User;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by xiaogang on 2018/9/8.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void login(String username, String password) {
        RetrofitManager.create(ApiServer.class)
                .login(username, password)
                .compose(RxSchedulers.<DataResponse<User>>applySchedulers())
                .compose(mView.<DataResponse<User>>bindToLife())
                .subscribe(new Consumer<DataResponse<User>>() {
                    @Override
                    public void accept(DataResponse<User> userDataResponse) throws Exception {
                        if (userDataResponse.getErrorCode() == 0) {
                            mView.loginSuccess(userDataResponse.getData());
                        } else {
              //              mView.showFaild(String.valueOf(userDataResponse.getErrorMsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }
}
