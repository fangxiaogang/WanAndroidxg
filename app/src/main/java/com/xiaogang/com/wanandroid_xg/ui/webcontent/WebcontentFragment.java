package com.xiaogang.com.wanandroid_xg.ui.webcontent;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
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

    @BindView(R.id.collectiv)
    ImageView mcollectiv;

    @BindView(R.id.titlecontent)
    TextView titlecontent;


    @BindView(R.id.mweblin)
    LinearLayout mweblin;

    @BindView(R.id.lincollect)
    LinearLayout mlincollect;

    @BindView(R.id.linshare)
    LinearLayout mlinshare;



    private String link,content;

    private int articleId;

    private static final String URL = "url";

    private static final String CONTENT = "content";

    private static final String ARTICLEID = "articleId";

    private static final String ISCOLLECT = "isCollect";

    private boolean isCollect;

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
        articleId = getArguments().getInt(ARTICLEID);
        isCollect= getArguments().getBoolean(ISCOLLECT);
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
                _mActivity.onBackPressed();

            }
        });
        moretitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
        mlincollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollect){
                    mPresenter.removeCollect(articleId);
                }else {
                    mPresenter.addCollect(articleId);
                }
            }
        });
        mlinshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, link);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
        if (isCollect) {
            mcollectiv.setImageResource(R.drawable.icon_collectsuccess);
        }else {
            mcollectiv.setImageResource(R.drawable.icon_collect);
        }
    }

    @Override
    public void addCollectSuccess(String msg) {
        ToastUtils.showShort(msg);
        isCollect = true;
        mcollectiv.setImageResource(R.drawable.icon_collectsuccess);
    }

    @Override
    public void removeCollectSuccess(String msg) {
        ToastUtils.showShort(msg);
        isCollect = false;
        mcollectiv.setImageResource(R.drawable.icon_collect);
    }


    public static WebcontentFragment newInstance(String url,String content,int id,boolean isCollect) {
        WebcontentFragment fragment = new WebcontentFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        args.putString(CONTENT, content);
        args.putInt(ARTICLEID,id);
        args.putBoolean(ISCOLLECT,isCollect);
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
