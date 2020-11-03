package com.ssyh.mydemo.test.process.communication.client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.ssyh.mydemo.test.utils.LogUtils;

public class OtherPMessagerService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("MessagerService onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
