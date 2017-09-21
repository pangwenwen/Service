package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hua.pang on 2017/9/20.
 */

public class ServiceMessenger extends Service {
    private static final String TAG = "ServiceMessenger";
    static final int SAY_HELLO = 1;

    class MessengerHander extends Handler{
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SAY_HELLO){
                Toast.makeText(getApplicationContext(),"HELLO,Beauty",Toast.LENGTH_LONG).show();

                Message message = Message.obtain();
                message.what = 10;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    Messenger messenger = new Messenger(new MessengerHander());
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return messenger.getBinder();
    }
}
