package com.ssyh.mydemo.test;

import android.app.Application;
import android.os.SystemClock;

import com.ssyh.mydemo.test.utils.LogUtils;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getAppContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtils.d("MyApplication onCreate");
        LogUtils.d("MyApplication pid == "+android.os.Process.myPid());
        //SystemClock.sleep(3000);
    }
}
