package com.example.android.bluetoothchat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;


public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>{

    private ArrayList<medinfo> mMedList;
    private Context mContext;




    public static class MedicationViewHolder extends RecyclerView.ViewHolder {
        public TextView mMedname;
        public TextView mTime;
        public TextView mNumbPills;
        public ImageView moremenu;

        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            mMedname = itemView.findViewById(R.id.medname);
            mTime = itemView.findViewById(R.id.time);
            mNumbPills = itemView.findViewById(R.id.numberofpills);
            moremenu = itemView.findViewById(R.id.menumore);
        }
    }
    public MedicationAdapter(ArrayList<medinfo> medList, Context context) {

        mMedList = medList;
        mContext = context;
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
        holder.moremenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        removeItem(position);

                        return true;
                    }
                });
            }
        });


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
