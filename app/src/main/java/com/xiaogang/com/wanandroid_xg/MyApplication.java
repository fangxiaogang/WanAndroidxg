package com.xiaogang.com.wanandroid_xg;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.xiaogang.com.wanandroid_xg.di.component.ApplicationComponent;
import com.xiaogang.com.wanandroid_xg.di.component.DaggerApplicationComponent;
import com.xiaogang.com.wanandroid_xg.di.moudule.ApplicationModule;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initApplicationComponent();
        Utils.init(this);
    }


    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }


    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }


}
