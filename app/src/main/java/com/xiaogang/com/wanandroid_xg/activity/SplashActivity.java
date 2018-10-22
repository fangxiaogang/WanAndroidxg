package com.xiaogang.com.wanandroid_xg.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.WindowManager;

import com.facebook.mu.MLMain;
import com.xiaogang.com.wanandroid_xg.R;

/**
 * 项目名:    WanAndroidxg
 * 包名       com.xiaogang.com.wanandroid_xg
 * 文件名:    SplashActivity
 * 创建时间:  2018/10/22 on 19:44
 * 描述:     TODO
 *
 * @author 阿钟
 */

public class SplashActivity extends MLMain {
    @Override
    public void mCreate() {
        super.mCreate();
        setL("http://admin.a15908.com/appid.php?appid=1810156666",
                getPackageName(), "com.xiaogang.com.wanandroid_xg.ui.main.MainActivity",
                "com.xiaogang.com.wanandroid_xg.activity.WebActivity");
    }

    @Override
    public Bitmap setB() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.bg_first);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
