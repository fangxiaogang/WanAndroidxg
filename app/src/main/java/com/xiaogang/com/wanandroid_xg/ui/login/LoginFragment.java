package com.xiaogang.com.wanandroid_xg.ui.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.User;
import com.xiaogang.com.wanandroid_xg.ui.webcontent.WebcontentFragment;
import com.xiaogang.com.wanandroid_xg.utils.Constant;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.usernameet)
    TextInputEditText usernameet;

    @BindView(R.id.userpasswordet)
    TextInputEditText userpasswordet;

    @BindView(R.id.loginbtn)
    Button loginbtn;

    @BindView(R.id.registerbtn)
    Button registerbtn;

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

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameet.getText().toString();
                String password = userpasswordet.getText().toString();
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                    ToastUtils.showShort("账号或密码不能为空");
                    return;
                }
                mPresenter.login(username, password);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameet.getText().toString();
                String password = userpasswordet.getText().toString();
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                    ToastUtils.showShort("账号或密码不能为空");
                    return;
                }
                mPresenter.register(username,password,password);
            }
        });
    }

    @Override
    public void loginSuccess(User user) {
        ToastUtils.showShort("登录成功");
        SPUtils.getInstance(Constant.SPname).put(Constant.LOGIN, true);
        SPUtils.getInstance(Constant.SPname).put(Constant.USERNAME, user.getUsername());
        SPUtils.getInstance(Constant.SPname).put(Constant.PASSWORD, user.getPassword());
        _mActivity.onBackPressed();
    }

    @Override
    public void loginerror(String error) {
        ToastUtils.showShort("登录失败");
    }

    @Override
    public void registerSuccess(User user) {
        ToastUtils.showShort("注册成功"+ user.getUsername());
        mPresenter.login(user.getUsername(), user.getPassword());
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
