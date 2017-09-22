package com.example.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hua.pang on 2017/9/21.
 */

public class XiaoLiZi implements Parcelable {
    private String sex;
    private String girlFriend;

    public static final Creator<XiaoLiZi> CREATOR = new Creator<XiaoLiZi>(){
        @Override
        public XiaoLiZi createFromParcel(Parcel parcel) {
            return new XiaoLiZi(parcel);
        }

        @Override
        public XiaoLiZi[] newArray(int i) {
            return new XiaoLiZi[i];
        }
    };

    public XiaoLiZi(){}
    public XiaoLiZi(Parcel in){
       readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sex);
        parcel.writeString(girlFriend);
    }


    public void readFromParcel(Parcel parcel){
        sex = parcel.readString();
        girlFriend = parcel.readString();
    }

    public String getSex() {
        return sex;
    }

    public String getGirlFriend() {
        return girlFriend;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGirlFriend(String girlFriend) {
        this.girlFriend = girlFriend;
    }
}
