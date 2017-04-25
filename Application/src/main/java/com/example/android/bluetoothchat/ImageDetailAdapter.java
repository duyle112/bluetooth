package com.example.android.bluetoothchat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.common.logger.Log;

import java.util.List;

/**
 * Created by HoangDuy on 28/12/2016.
 */
public class ImageDetailAdapter extends RecyclerView.Adapter<ImageDetailAdapter.ViewHolder> {
    private List<Capacity> capacities;

    public ImageDetailAdapter(List<Capacity> capacities) {
        this.capacities = capacities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvDeviceName.setText(capacities.get(position).getPhoneName());
        double bytes = capacities.get(position).getSendedMb() / (1024 * 1024);
        Log.i("bytes",bytes+"");
        holder.mTvBytes.setText((double)Math.round(bytes * 1000) / 1000 + "Mb");
    }

    @Override
    public int getItemCount() {
        return capacities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvDeviceName;
        TextView mTvBytes;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvDeviceName = (TextView) itemView.findViewById(R.id.tvDeviceName);
            mTvBytes = (TextView) itemView.findViewById(R.id.tvBytes);
        }
    }
}
