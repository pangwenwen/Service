// IMyAidlInterface.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements
import com.example.aidlserver.XiaoLiZi;
interface IMyAidlInterface {
void findGirlFriend(inout XiaoLiZi x);
    int addInt(int a,int b);
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
