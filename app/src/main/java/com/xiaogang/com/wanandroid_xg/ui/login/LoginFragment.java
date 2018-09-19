package com.xiaogang.com.wanandroid_xg.ui.login;

import android.view.View;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.User;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void loginSuccess(User user) {

    }

    @Override
    public void loginerror(String error) {

    }
}
