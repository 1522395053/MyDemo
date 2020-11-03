package com.ssyh.mydemo.test.utils;

import android.util.Log;

public class LogUtils {
    private static final boolean LOG_ENABLE = true;
    private static final String TAG = "LogUtils";
    public static void d(String log){
        if (LOG_ENABLE){
            Log.d(TAG, log);
        }
    }
}
