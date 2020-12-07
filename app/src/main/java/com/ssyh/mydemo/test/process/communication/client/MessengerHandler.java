package com.ssyh.mydemo.test.process.communication.client;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ssyh.mydemo.test.process.communication.Constants;
import com.ssyh.mydemo.test.utils.ToastUtils;

public class MessengerHandler extends Handler {


    @Override
    public void handleMessage(@NonNull Message msgFromClient) {
        super.handleMessage(msgFromClient);
        switch (msgFromClient.what){
            case Constants.MSG_FROM_SERVER:
                String message = msgFromClient.getData().getString(Constants.MESSAGE);
                ToastUtils.toastS("from-service:\n "+message);



                break;
        }
    }
}
