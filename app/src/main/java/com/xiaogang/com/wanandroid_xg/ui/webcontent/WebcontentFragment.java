package com.xiaogang.com.wanandroid_xg.ui.webcontent;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.SupportFragment;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/21
 */

public class WebcontentFragment extends BaseFragment<WebcontentPresenter> implements WebcontentContract.View {

    @BindView(R.id.backtitle)
    ImageView backtitle;

    @BindView(R.id.moretitle)
    ImageView moretitle;

    @BindView(R.id.titlecontent)
    TextView titlecontent;


    @BindView(R.id.mweblin)
    LinearLayout mweblin;

    private String link;

    private String content;

    private static final String URL = "url";

    private static final String CONTENT = "content";

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webcontent;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        link = getArguments().getString(URL);
        content = getArguments().getString(CONTENT);
    }

    @Override
    protected void initView(View view) {
        mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(mweblin, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go(link);
        titlecontent.setText(content);
        backtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // 任意同栈内的Fragment中：
//                MainFragment fragment = findFragment(MainFragment.class);
//                Bundle newBundle = new Bundle();
//                fragment.putNewBundle(newBundle);
//                // 在栈内的HomeFragment以SingleTask模式启动（即在其之上的Fragment会出栈）
//                start(fragment, SupportFragment.SINGLETASK);

                _mActivity.onBackPressed();

            }
        });

    }



    public static WebcontentFragment newInstance(String url,String content) {
        WebcontentFragment fragment = new WebcontentFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        args.putString(CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onBackPressedSupport() {
       if (mAgentWeb.back())  {
           mAgentWeb.back();
           return true;
       }else {
           return false;
       }
    }

}
