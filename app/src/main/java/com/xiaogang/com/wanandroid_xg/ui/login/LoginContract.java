package com.xiaogang.com.wanandroid_xg.ui.login;

import com.xiaogang.com.wanandroid_xg.base.BaseContract;
import com.xiaogang.com.wanandroid_xg.bean.User;

/**
 * Created by xiaogang on 2018/9/6.
 */

public interface LoginContract {

    interface View extends BaseContract.Baseview {

        void loginSuccess(User user);

        void loginerror(String error);

        void registerSuccess(User user);
    }

    interface Presenter extends BaseContract.Basepresenter<LoginContract.View> {
        void login(String username, String password);

        void register(String username, String password,String repassword);
    }

}
