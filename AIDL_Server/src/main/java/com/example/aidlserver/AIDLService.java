package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by hua.pang on 2017/9/21.
 */

public class AIDLService extends Service {
    private static final String TAG = "AIDLService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {

        @Override
        public void findGirlFriend(XiaoLiZi x) throws RemoteException {
            x.setGirlFriend("美丽的小花");
        }

        @Override
        public int addInt(int a, int b) throws RemoteException {
            Log.d(TAG, "addInt: a = "+a+"; b = "+b);
            return a+b;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

}

