package com.xiaogang.com.wanandroid_xg.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.xiaogang.com.wanandroid_xg.MyApplication;
import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Banner;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.bean.User;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.Constant;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;
import com.xiaogang.com.wanandroid_xg.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: fangxiaogang
 * date: 2018/9/17
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private int mPage = 0;

    private boolean isRefresh = true;

    @Inject
    public HomePresenter() {

    }

    @Override
    public void getBannerdate() {
        RetrofitManager.create(ApiServer.class)
                .getHomeBanners()
                .compose(RxSchedulers.<DataResponse<List<Banner>>>applySchedulers())
                .compose(mView.<DataResponse<List<Banner>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<Banner>>>() {
                    @Override
                    public void accept(DataResponse<List<Banner>> listDataResponse) throws Exception {
                        mView.setBannerdate(listDataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    @Override
    public void gethomedate() {
        RetrofitManager.create(ApiServer.class)
                .getHomeArticles(mPage)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> articleDataResponse) throws Exception {
                        if (isRefresh) {
                            mView.sethomedate(articleDataResponse.getData(), 0);
                        } else {
                            mView.sethomedate(articleDataResponse.getData(), 1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

        if (SPUtils.getInstance(Constant.SPname).getBoolean(Constant.LOGIN)) {
            String username = SPUtils.getInstance(Constant.SPname).getString(Constant.USERNAME);
            String password = SPUtils.getInstance(Constant.SPname).getString(Constant.PASSWORD);
            RetrofitManager.create(ApiServer.class)
                    .login(username, password)
                    .compose(RxSchedulers.<DataResponse<User>>applySchedulers())
                    .compose(mView.<DataResponse<User>>bindToLife())
                    .subscribe(new Consumer<DataResponse<User>>() {
                        @Override
                        public void accept(DataResponse<User> userDataResponse) throws Exception {
                            if (userDataResponse.getErrorCode() == 0) {

                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
        }

    }


    @SuppressLint("CheckResult")
    @Override
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

    @Override
    public void refresh() {
        mPage = 0;
        isRefresh = true;
        getBannerdate();
        gethomedate();
    }

    @Override
    public void loadMore() {
        mPage++;
        isRefresh = false;
        gethomedate();
    }


}
