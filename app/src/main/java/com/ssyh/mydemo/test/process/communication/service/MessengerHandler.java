package com.ssyh.mydemo.test.process.communication.service;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
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
            case Constants.MSG_FROM_CLIENT:
                String message = msgFromClient.getData().getString(Constants.MESSAGE);
                ToastUtils.toastS("from-client:\n "+message);


                Message msgToClient = Message.obtain(msgFromClient);
                msgToClient.what = Constants.MSG_FROM_SERVER;
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MESSAGE,"你好，客户端");
                msgToClient.setData(bundle);
                try {
                    msgFromClient.replyTo.send(msgToClient);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
