package com.xiaogang.com.wanandroid_xg.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 项目名:    VestApp
 * 包名       com.azhon.vest.utils
 * 文件名:    Utils
 * 创建时间:  2018/9/29 on 11:09
 * 描述:     TODO
 *
 * @author 阿钟
 */

public class Utils {

    //获取手机的唯一标识
    public static String getPhoneSign(Context context) {
        StringBuilder deviceId = new StringBuilder();
        try {
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!TextUtils.isEmpty(imei)) {
                deviceId.append("imei-");
                deviceId.append(imei);
                return deviceId.toString();
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("sn-");
                deviceId.append(sn);
                return deviceId.toString();
            }
            //如果上面都没有
            String androidId = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
            if (!TextUtils.isEmpty(androidId)) {
                deviceId.append("androidId-");
                deviceId.append(androidId);
                return deviceId.toString();
            }
            serial(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            serial(deviceId);
        }
        return deviceId.toString();
    }

    private static void serial(StringBuilder deviceId) {
        String serial = Build.BRAND + Build.MODEL;
        deviceId.append("serial-")
                .append(serial);
    }
}
