package com.xiaogang.com.wanandroid_xg.net;

import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.Banner;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.bean.Knowledge;
import com.xiaogang.com.wanandroid_xg.bean.Project;
import com.xiaogang.com.wanandroid_xg.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * author: fangxiaogang
 * date: 2018/9/1
 */

public interface ApiServer {

    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<DataResponse<User>> login(@Field("username") String username, @Field("password") String password);

    //我的收藏
    @GET("/lg/collect/list/{page}/json")
    Observable<DataResponse<Article>> getMyCollect(@Path("page") int page);

    //首页Banner
    @GET("/banner/json")
    Observable<DataResponse<List<Banner>>> getHomeBanners();

    //首页数据
    @GET("/article/list/{page}/json")
    Observable<DataResponse<Article>> getHomeArticles(@Path("page") int page);

    //注册
    @POST("/user/register")
    @FormUrlEncoded
    Observable<DataResponse<User>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    //知识体系
    //http://www.wanandroid.com/tree/json
    @GET("/tree/json")
    Observable<DataResponse<List<Knowledge>>> getKnowledge();

    //项目
    //http://www.wanandroid.com/project/tree/json
    @GET("/project/tree/json")
    Observable<DataResponse<List<Project>>> getProject();

    //添加收藏
    @POST("/lg/collect/{id}/json")
    Observable<DataResponse> addCollect(@Path("id") int id);

}
