package com.xiaogang.com.wanandroid_xg.ui.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Project;

import java.util.List;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void setProjectDate(List<Project> projects) {

    }


    public static ProjectFragment newInstance() {
        Bundle args = new Bundle();
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }



    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
