package com.example.android.bluetoothchat;

import android.graphics.Bitmap;

/**
 * Created by HoangDuy on 19/12/2016.
 */
public class Item {

    private Bitmap bitmap;
    private String nameDevice;
    private byte[] bytes;
    private int length;

    public Item(Bitmap bitmap, String nameDevice, byte[] bytes, int length) {
        this.bitmap = bitmap;
        this.nameDevice = nameDevice;
        this.bytes = bytes;
        this.length = length;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
