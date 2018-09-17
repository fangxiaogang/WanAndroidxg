package com.xiaogang.com.wanandroid_xg.di.component;

import android.app.Activity;
import android.content.Context;

import com.xiaogang.com.wanandroid_xg.di.moudule.FragmentModule;
import com.xiaogang.com.wanandroid_xg.di.scope.ContextLife;
import com.xiaogang.com.wanandroid_xg.di.scope.PerFragment;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeFragment;


import dagger.Component;

/**
 * author: fangxiaogang
 * date: 2018/9/13
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeFragment fragment);

}