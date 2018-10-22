package com.xiaogang.com.wanandroid_xg.ui.main;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseActivity;
import com.xiaogang.com.wanandroid_xg.ui.login.LoginFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        loadRootFragment(R.id.mhoneframeLayout, LoginFragment.newInstance());
    }

    @Override
    protected void initInjector() {

    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }


}
