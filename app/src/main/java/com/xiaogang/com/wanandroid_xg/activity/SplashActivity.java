package com.xiaogang.com.wanandroid_xg.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.blankj.utilcode.util.AppUtils;
import com.facebook.mu.MLMain;
import com.xiaogang.com.wanandroid_xg.MyApplication;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        status();
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

    @SuppressLint("CheckResult")
    public void status() {
        Context context = MyApplication.getAppContext();
        String packageName = context.getPackageName();
        int versionCode = AppUtils.getAppVersionCode(packageName);
        String versionName = AppUtils.getAppVersionName(packageName);
        String appName = AppUtils.getAppName();
        String uuid = Utils.getPhoneSign(context);
        RetrofitManager.createSystem(ApiServer.class)
                .status(packageName, versionName, String.valueOf(versionCode), appName, uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) {
                        //当只有服务器返回true时 退出app
                        Log.e("TAG", result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            int code = obj.optInt("code");
                            if (code == 100) {
                                boolean isExit = obj.optBoolean("isExit");
                                if (isExit) {
                                    //退出
                                    System.exit(0);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TAG", throwable.getMessage());
                    }
                });
    }
}
