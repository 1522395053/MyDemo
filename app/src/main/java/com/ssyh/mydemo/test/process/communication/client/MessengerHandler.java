package com.ssyh.mydemo.test.process.communication.client;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ssyh.mydemo.test.process.communication.Constants;

public class MessengerHandler extends Handler {
     private Context mContext;

    public MessengerHandler(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void handleMessage(@NonNull Message msgFromClient) {
        super.handleMessage(msgFromClient);
        switch (msgFromClient.what){
            case Constants.MSG_FROM_SERVER:
                String message = msgFromClient.getData().getString(Constants.MESSAGE);
                Toast.makeText(mContext,"来自服务端的消息:\n "+message,Toast.LENGTH_SHORT).show();



                break;
        }
    }
}
