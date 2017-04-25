package com.example.android.bluetoothchat;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.common.logger.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by HoangDuy on 19/12/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Item> mItems;
    public onAdapterListener mListener;
    public MyAdapter(List<Item> items) {
        mItems = items;
        Log.i("infof", mItems.size() + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mListener = (onAdapterListener)parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.img.setImageBitmap(mItems.get(position).getBitmap());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mItems.get(position).getBitmap().compress(Bitmap.CompressFormat.PNG,100,stream);
                mListener.onAdapterTransaction(stream.toByteArray());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.tvDeviceName);
            img = (ImageView) itemView.findViewById(R.id.imgReceive);
        }
    }

    interface onAdapterListener {
        void onAdapterTransaction(byte [] bytes);
    }
}
