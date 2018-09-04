package com.xiaogang.com.wanandroid_xg.di.component;

import android.content.Context;


import com.xiaogang.com.wanandroid_xg.di.moudule.ApplicationModule;
import com.xiaogang.com.wanandroid_xg.di.scope.ContextLife;
import com.xiaogang.com.wanandroid_xg.di.scope.PerApp;

import dagger.Component;


/**
 * Created by lw on 2017/1/19.
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}