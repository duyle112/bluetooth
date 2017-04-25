package com.example.android.bluetoothchat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ImageDetail extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RealmController realmController = new RealmController(getApplicationContext());
        List<Capacity> capacities = realmController.getCapacity();
        mAdapter  = new ImageDetailAdapter(capacities);
        mLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(mAdapter);
    }
}
