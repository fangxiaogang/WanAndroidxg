package com.xiaogang.com.wanandroid_xg.di.component;

import android.app.Activity;
import android.content.Context;

import com.xiaogang.com.wanandroid_xg.di.moudule.FragmentModule;
import com.xiaogang.com.wanandroid_xg.di.scope.ContextLife;
import com.xiaogang.com.wanandroid_xg.di.scope.PerFragment;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeFragment;
import com.xiaogang.com.wanandroid_xg.ui.knowledge.KnowledgeFragment;
import com.xiaogang.com.wanandroid_xg.ui.knowledge.KnowledgeLIstFragment;
import com.xiaogang.com.wanandroid_xg.ui.login.LoginFragment;
import com.xiaogang.com.wanandroid_xg.ui.mycollect.MyCollectFragment;
import com.xiaogang.com.wanandroid_xg.ui.project.ArticleFragment;
import com.xiaogang.com.wanandroid_xg.ui.project.ProjectFragment;
import com.xiaogang.com.wanandroid_xg.ui.search.SearchFragment;
import com.xiaogang.com.wanandroid_xg.ui.webcontent.WebcontentFragment;


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

    void inject(KnowledgeFragment fragment);

    void inject(ProjectFragment fragment);

    void inject(LoginFragment fragment);

    void inject(MyCollectFragment fragment);

    void inject(WebcontentFragment fragment);

    void inject(ArticleFragment fragment);

    void inject(KnowledgeLIstFragment fragment);

   // void inject(SearchFragment fragment);

}