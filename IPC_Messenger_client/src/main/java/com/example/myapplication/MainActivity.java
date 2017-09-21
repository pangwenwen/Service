package com.example.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Messenger mMessenger;
    private boolean mIsBind;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView) findViewById(R.id.text);
    }


    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            mIsBind = true;
            mMessenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIsBind = false;
        }
    };

    public void bindS(View view){
        //5.0 以后不能隐士启动 service
        Intent intent = new Intent();
        ComponentName component = new ComponentName("com.example.messenger.server", "com.example.myapplication.ServiceMessenger");
        intent.setComponent(component);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
        //startService(intent);
    }

    class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Log.e(TAG, "handleMessage: msg.what = "+msg.what);
            if (msg.what == 10){
                mText.setText(msg.what+"");
            }
        }
    }

    /**
     * 用于和服务端交互的信使
     */
    Messenger myMessenger = new Messenger(new MessengerHandler());

    public void sendS(View view){
        if(!mIsBind) return;
        Message message = Message.obtain();
        message.what = 1;
        message.replyTo = myMessenger;
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!mIsBind) return;
        unbindService(mConnection);
    }
}
