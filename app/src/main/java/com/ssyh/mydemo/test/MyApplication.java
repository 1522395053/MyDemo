package com.ssyh.mydemo.test;

import android.app.Application;
import android.os.SystemClock;

import com.ssyh.mydemo.test.utils.LogUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("MyApplication onCreate");
        LogUtils.d("MyApplication pid == "+android.os.Process.myPid());
        //SystemClock.sleep(3000);
    }
}
