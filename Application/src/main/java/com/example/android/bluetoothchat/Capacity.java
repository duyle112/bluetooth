package com.example.android.bluetoothchat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HoangDuy on 28/12/2016.
 */
public class Capacity extends RealmObject {

    @PrimaryKey
    private String phoneName;
    private double sendedMb;

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public double getSendedMb() {
        return sendedMb;
    }

    public void setSendedMb(double sendedMb) {
        this.sendedMb = sendedMb;
    }

    public Capacity(String phoneName, double sendedMb) {
        this.phoneName = phoneName;
        this.sendedMb = sendedMb;
    }
    public Capacity() {

    }
}
