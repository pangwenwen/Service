package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aidlserver.IMyAidlInterface;
import com.example.aidlserver.XiaoLiZi;


public class MainActivity extends AppCompatActivity {
     IMyAidlInterface myAidlInterface;
    boolean isBound;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myAidlInterface = null;
            isBound = false;
        }
    };

    public void bindService(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.aidlserver","com.example.aidlserver.AIDLService");
        intent.setComponent(componentName);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void translate(View view){
        if (isBound){
            try {
                int add = myAidlInterface.addInt(2,3);
                Toast.makeText(getApplicationContext(),add+"",Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    public void findGirl(View view){
        if (isBound){
            try {
                XiaoLiZi xiaoLiZi = new XiaoLiZi();
                xiaoLiZi.setSex("男");
                myAidlInterface.findGirlFriend(xiaoLiZi);
                Toast.makeText(getApplicationContext(),xiaoLiZi.getGirlFriend(),Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
