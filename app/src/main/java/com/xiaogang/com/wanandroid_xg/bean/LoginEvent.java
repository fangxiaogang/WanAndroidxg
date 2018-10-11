package com.xiaogang.com.wanandroid_xg.bean;

/**
 * Created by xiaogang on 2018/10/11.
 */

public class LoginEvent {

    private String message;
    public  LoginEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
