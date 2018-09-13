package com.xiaogang.com.wanandroid_xg.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xiaogang.com.wanandroid_xg.di.component.ActivityComponent;
import com.xiaogang.com.wanandroid_xg.MyApplication;
import com.xiaogang.com.wanandroid_xg.di.component.DaggerActivityComponent;
import com.xiaogang.com.wanandroid_xg.di.moudule.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.xiaogang.com.wanandroid_xg.SupportActivity;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public abstract class BaseActivity <T extends BaseContract.Basepresenter> extends SupportActivity implements BaseContract.Baseview{

    @Nullable
    @Inject
    protected T mPresenter;

    protected ActivityComponent mActivityComponent;


    private Unbinder unbinder;

    protected abstract int getLayoutId();

    protected abstract void initView();


    protected abstract void initInjector();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initInjector();
        attachView();
        unbinder = ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化ActivityComponent
     */
    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        destroyView();
    }


    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachview(this);
        }
    }

    /**
     * 分离view
     */
    private void destroyView() {
        if (mPresenter != null) {
            mPresenter.destroyview();
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

}
