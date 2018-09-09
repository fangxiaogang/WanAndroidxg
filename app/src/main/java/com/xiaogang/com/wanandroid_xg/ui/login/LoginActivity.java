package com.xiaogang.com.wanandroid_xg.ui.login;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseActivity;
import com.xiaogang.com.wanandroid_xg.bean.User;

/**
 * Created by xiaogang on 2018/9/9.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View  {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initInjector() {

    }


    @Override
    public void loginSuccess(User user) {

    }

    @Override
    public void loginerror(String error) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showFaild(String message) {

    }

    @Override
    public <V> LifecycleTransformer<V> bindToLife() {
        return null;
    }


}
