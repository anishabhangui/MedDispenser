package com.example.android.bluetoothchat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>{

    private ArrayList<medinfo> mMedList;


    public static class MedicationViewHolder extends RecyclerView.ViewHolder {
        public TextView mMedname;
        public TextView mTime;
        public TextView mNumbPills;

        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            mMedname = itemView.findViewById(R.id.medname);
            mTime = itemView.findViewById(R.id.time);
            mNumbPills = itemView.findViewById(R.id.numberofpills);
        }
    }
    public MedicationAdapter(ArrayList<medinfo> medList) {
        mMedList = medList;
    }

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        MedicationViewHolder mvh = new MedicationViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        medinfo currentItem = mMedList.get(position);
        String medName = "Medication: " + currentItem.getmMedName();
        holder.mMedname.setText(medName);
        String medTime = "Time: " + currentItem.getmTime();
        holder.mTime.setText(medTime);
        String numbPills = "# of Pills: " + currentItem.getmNumbPills();
        holder.mNumbPills.setText(numbPills);

    }


    @Override
    public int getItemCount() {
        return mMedList.size();
    }

    public void removeItem(int position) {
        mMedList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mMedList.size());
    }


}
