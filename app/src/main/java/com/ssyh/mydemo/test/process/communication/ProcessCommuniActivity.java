package com.ssyh.mydemo.test.process.communication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ssyh.mydemo.R;
import com.ssyh.mydemo.test.process.communication.client.MessengerHandler;
import com.ssyh.mydemo.test.utils.LogUtils;
import com.ssyh.mydemo.test.utils.ToastUtils;

public class ProcessCommuniActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int MSG_SUM = 0x110;

    private Button mBtnAdd;
    private LinearLayout mLyContainer;
    //显示连接状态
    private TextView mTvState;

    private Messenger mService;
    private boolean isConn;

    private Messenger mMessenger = new Messenger(new MessengerHandler());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("ProcessCommuniActivity onCreate");
        setContentView(R.layout.activity_process_communi);


        mTvState = (TextView) findViewById(R.id.id_tv_callback);
        mTvState.setText(getPackageName());
        mBtnAdd = (Button) findViewById(R.id.id_btn_add);
        mLyContainer = (LinearLayout) findViewById(R.id.id_ll_container);
        findViewById(R.id.btn_bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始绑定服务
                bindServiceInvoked();

            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = mA++;
                    int b = (int) (Math.random() * 100);

                    /*//创建一个tv,添加到LinearLayout中
                    TextView tv = new TextView(ProcessCommuniActivity.this);
                    tv.setText(a + " + " + b + " = caculating ...");
                    tv.setId(a);
                    mLyContainer.addView(tv);*/

                    Message msgToServer = Message.obtain(null, Constants.MSG_FROM_CLIENT/*, a, b*/);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.MESSAGE,"服务端，你好");
                    msgToServer.setData(bundle);
                    msgToServer.replyTo = mMessenger;
                    if (isConn) {
                        //往服务端发送消息
                        mService.send(msgToServer);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            isConn = true;
            mTvState.setText("connected!");
            ToastUtils.toastL("绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isConn = false;
            mTvState.setText("disconnected!");
        }
    };

    private int mA;



    private void bindServiceInvoked() {
        Intent intent = new Intent();
        intent.setAction("com.ssyh.service.p");
        intent.setPackage("com.ssyh.mydemo.service");
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }
}