package com.example.android.bluetoothchat;

import android.content.Context;

import java.util.List;

import io.realm.Realm;

/**
 * Created by HoangDuy on 28/12/2016.
 */
public class RealmController  {
    private Realm mRealm;
    private Context mContext;
    public RealmController(Context context) {
        mContext = context;
        Realm.init(mContext);
        mRealm = Realm.getDefaultInstance();
    }

    public List<Capacity> getCapacity() {
        return mRealm.where(Capacity.class).findAll();
    }

    public void update(Capacity capacity) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(capacity);
        mRealm.commitTransaction();
    }
}
