package com.xiaogang.com.wanandroid_xg.di.moudule;

import android.content.Context;


import com.xiaogang.com.wanandroid_xg.MyApplication;
import com.xiaogang.com.wanandroid_xg.di.scope.ContextLife;
import com.xiaogang.com.wanandroid_xg.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;


/**
 * Created by lw on 2017/1/19.
 */
@Module
public class ApplicationModule {
    private MyApplication mApplication;

    public ApplicationModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
