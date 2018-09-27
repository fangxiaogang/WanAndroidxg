package com.xiaogang.com.wanandroid_xg.ui.login;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.User;
import com.xiaogang.com.wanandroid_xg.ui.webcontent.WebcontentFragment;

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
        mPresenter.login("17620359266", "123456");
    }

    @Override
    public void loginSuccess(User user) {
        ToastUtils.showShort("登录成功");
    }

    @Override
    public void loginerror(String error) {
        ToastUtils.showShort("登录失败");
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
