package com.xiaogang.com.wanandroid_xg.ui.webcontent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.base.BaseSwipeBackFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * author: fangxiaogang
 * date: 2018/9/21
 */

public class WebcontentFragment2 extends BaseSwipeBackFragment {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webcontent, container, false);
        return attachToSwipeBack(view);
    }

    public static WebcontentFragment2 newInstance(String url) {
        WebcontentFragment2 fragment = new WebcontentFragment2();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

}
