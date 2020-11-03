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

public class MessengerHandler extends Handler {
     private Context mContext;

    public MessengerHandler(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void handleMessage(@NonNull Message msgFromClient) {
        super.handleMessage(msgFromClient);
        switch (msgFromClient.what){
            case Constants.MSG_FROM_CLIENT:
                String message = msgFromClient.getData().getString(Constants.MESSAGE);
                Toast.makeText(mContext,"来自客户端的消息:\n "+message,Toast.LENGTH_SHORT).show();


                Message msgToClient = Message.obtain(msgFromClient);
                msgToClient.what = Constants.MSG_FROM_SERVER;
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MESSAGE,"我是服务端的消息");
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
