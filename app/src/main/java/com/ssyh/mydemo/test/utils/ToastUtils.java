package com.ssyh.mydemo.test.utils;

import android.util.Log;
import android.widget.Toast;

import com.ssyh.mydemo.test.MyApplication;

public class ToastUtils {
    private static final boolean ENABLE = true;
    public static void toastS(String text){
        if (ENABLE){
            Toast.makeText(MyApplication.getAppContext(),text,Toast.LENGTH_SHORT).show();
        }
    }
    public static void toastL(String text){
        if (ENABLE){
            Toast.makeText(MyApplication.getAppContext(),text,Toast.LENGTH_LONG).show();
        }
    }
}
