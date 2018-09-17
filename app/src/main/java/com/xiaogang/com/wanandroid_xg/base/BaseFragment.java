package com.xiaogang.com.wanandroid_xg.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.NetworkUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxFragment;
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
//        mFragmentComponent = DaggerFragmentComponent.builder()
//                .applicationComponent(((MyApplication) getActivity().getApplication()).getApplicationComponent())
//                .fragmentModule(new FragmentModule(this))
//                .build();

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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showFaild(String message) {

    }

    @Override
    public <V> LifecycleTransformer<V> bindToLife() {
        return this.bindToLifecycle();
    }
}
