package com.xiaogang.com.wanandroid_xg;

import android.app.Application;
import android.content.Context;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }


    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }
}
