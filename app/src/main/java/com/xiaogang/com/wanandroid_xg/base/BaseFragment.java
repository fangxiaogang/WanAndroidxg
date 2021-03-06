package com.xiaogang.com.wanandroid_xg.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.classic.common.MultipleStatusView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xiaogang.com.wanandroid_xg.MyApplication;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.SupportFragment;
import com.xiaogang.com.wanandroid_xg.di.component.DaggerFragmentComponent;
import com.xiaogang.com.wanandroid_xg.di.component.FragmentComponent;
import com.xiaogang.com.wanandroid_xg.di.moudule.FragmentModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: fangxiaogang
 * date: 2018/9/13
 */

public abstract class BaseFragment < T extends BaseContract.Basepresenter> extends SupportFragment implements BaseContract.Baseview {

    @Nullable
    @Inject
    protected T mPresenter;

    protected FragmentComponent mFragmentComponent;

    private Unbinder unbinder;

    @Nullable
    @BindView(R.id.MultipleStatusView)
    MultipleStatusView multipleStatusView;

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract void initView(View view);

    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        initInjector();
        attachView();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflaterView(inflater, container);
        unbinder = ButterKnife.bind(this, mRootView);
        showLoading();
        initView(mRootView);
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        detachView();
    }


    private void inflaterView(LayoutInflater inflater, @Nullable ViewGroup container) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
    }


    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
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
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.destroyview();
        }
    }

    @Override
    public void showLoading() {
        if(multipleStatusView != null ){
            multipleStatusView.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if(multipleStatusView != null ){
            multipleStatusView.showContent();
        }
    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showFaild(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public <V> LifecycleTransformer<V> bindToLife() {
        return this.bindToLifecycle();
    }
}
