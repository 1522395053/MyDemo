package com.ssyh.mydemo.test.process.communication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

import androidx.annotation.Nullable;

public class MessengerService extends Service {
    private static final int MSG_FROM_CLIENT = 0x1;

    private Messenger messenger = new Messenger(new MessengerHandler(getApplicationContext()));


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
