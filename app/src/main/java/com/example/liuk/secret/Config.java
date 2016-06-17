package com.example.liuk.secret;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * Created by Administrator on 2016/6/7.
 */
public class Config {

    public static final String KEY_ACTION = "action";
    public static final String KEY_PHONE_NUM = "phone";
    public static final String KEY_STATUS = "status";

    public static final String KEY_TOKEN = "token";
    public static final String APP_ID = "com.example.liuk.secret";
    public static final String CHARSET = "utf-8";
    //public static final String SERVER_URL = "http://demo.eoeschool.com/api/v1/nimings/io";
    public static final String SERVER_URL = "http://192.168.1.100:8080/TestServer/api.jsp?action=send_pass";


    public static final String ACTION_GETCODE = "send_pass";

    public static final int RESULT_STATUS_SUCCESS = 1;
    public static final int RESULT_STATUS_FAIL = 0;
    public static final int RESULT_STATUS_INVALID_TOKEN = 2;

    public static String getCachedToken(Context context)
    {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    public static void cacheToken(Context context, String token)
    {
        Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }
}
