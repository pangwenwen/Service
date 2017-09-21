package com.example.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Messenger mMessenger;
    boolean mIsBind;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMessenger = new Messenger(iBinder);
            mIsBind = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIsBind = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindS(View view){
        //bindService(new Intent(this,ServiceMessenger.class),mConnection, Context.BIND_AUTO_CREATE);
        startService(new Intent(this,ServiceMessenger.class));
    }

    public void sendS(View view){
        if (!mIsBind) return;
        Message message = Message.obtain();
        message.what = ServiceMessenger.SAY_HELLO;
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
