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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    ArrayList<DayHeader> weekList;
    private Context mContext;
    ItemTouchHelper mItemTouchHelper;

    public MainRecyclerViewAdapter(Context mContext, ArrayList<DayHeader> weekList, ItemTouchHelper itemTouchHelper) {
        this.mContext = mContext;
        this.weekList = weekList;
        this.mItemTouchHelper = itemTouchHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.dayofweekheader,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DayHeader section = weekList.get(position);
        String nameofday = section.getDayName();
        ArrayList<medinfo> meditems = section.getmMedItems();
        holder.weekdayname.setText(nameofday);
        MedicationAdapter childrecyclerAdapter = new MedicationAdapter(meditems);
        holder.childrecyclerview.setAdapter(childrecyclerAdapter);
        mItemTouchHelper = new ItemTouchHelper(new SwipeItem(childrecyclerAdapter));

        //String time_test = meditems.get(3).getmTime();


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Date time = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentTime = new SimpleDateFormat("KK:mm aa", Locale.getDefault()).format(new Date());
        //ArrayList<medinfo> med_monday = weekList.get(0).mMedItems;
        //Log.d("monday time", med_monday.get(0).getmTime());
        //Log.d("size",String.valueOf(med_monday.size()));
        //Log.d("current time", currentTime);
        switch (day) {
            case Calendar.MONDAY:
                if(nameofday.equals("Monday")&&weekList.get(0).mMedItems.size()!=0){
                    ArrayList<medinfo> med_monday = weekList.get(0).mMedItems;
                    Integer mon_length = med_monday.size();
                    for(int i=0;i<mon_length;i++){
                        String med_monday_time = med_monday.get(i).getmTime();
                        if(med_monday_time.equals(currentTime)){
                            //build notification
                            CreateMedNotification(med_monday.get(i).getmMedName(),position);
                            med_monday.get(i).setNotificationShow(false);
                        }
                    }
                }
                break;
            case Calendar.TUESDAY:
                if(nameofday.equals("Tuesday")&&weekList.get(1).mMedItems.size()!=0){
                    ArrayList<medinfo> med_tuesday = weekList.get(1).mMedItems;
                    Integer tues_length = med_tuesday.size();
                    for(int i=0;i<tues_length;i++){
                        String med_tuesday_time = med_tuesday.get(i).getmTime();
                        if(med_tuesday_time.equals(currentTime)){
                            CreateMedNotification(med_tuesday.get(i).getmMedName(),position);
                            med_tuesday.get(i).setNotificationShow(false);
                        }
                    }
                }
                break;
            case Calendar.WEDNESDAY:
                if(nameofday.equals("Wednesday")&&weekList.get(2).mMedItems.size()!=0){
                    ArrayList<medinfo> med_wednesday = weekList.get(2).mMedItems;
                    Integer wed_length = med_wednesday.size();
                    for(int i=0;i<wed_length;i++){
                        String med_wednesday_time = med_wednesday.get(i).getmTime();
                        if(med_wednesday_time.equals(currentTime)){
                            //build notification
                            CreateMedNotification(med_wednesday.get(i).getmMedName(),position);
                            med_wednesday.get(i).setNotificationShow(false);
                        }
                    }

                }
                break;
            case Calendar.THURSDAY:
                if(nameofday.equals("Thursday")&&weekList.get(3).mMedItems.size()!=0){
                    ArrayList<medinfo> med_thursday = weekList.get(3).mMedItems;
                    Integer thurs_length = med_thursday.size();
                    for(int i=0;i<thurs_length;i++){
                        String med_thursday_time = med_thursday.get(i).getmTime();
                        if(med_thursday_time.equals(currentTime)){
                            //build notification
                            CreateMedNotification(med_thursday.get(i).getmMedName(),position);
                            med_thursday.get(i).setNotificationShow(false);
                        }
                    }
                }
                break;
            case Calendar.FRIDAY:
                if(nameofday.equals("Friday")){
                    ArrayList<medinfo> med_friday = weekList.get(4).mMedItems;
                    Integer fri_length = med_friday.size();
                    for(int i=0;i<fri_length;i++){
                        String med_friday_time = med_friday.get(i).getmTime();
                        if(med_friday_time.equals(currentTime)){
                            //build notification
                            CreateMedNotification(med_friday.get(i).getmMedName(),position);
                            med_friday.get(i).setNotificationShow(false);
                        }
                    }
                }
                break;
            case Calendar.SATURDAY:
                if(nameofday.equals("Saturday")){
                    ArrayList<medinfo> med_saturday = weekList.get(5).mMedItems;
                    Integer sat_length = med_saturday.size();
                    for(int i=0;i<sat_length;i++){
                        String med_saturday_time = med_saturday.get(i).getmTime();
                        if(med_saturday_time.equals(currentTime)){
                            Log.d("test", "it works");
                            CreateMedNotification(med_saturday.get(i).getmMedName(),position);
                            med_saturday.get(i).setNotificationShow(false);
                        }
                    }

                }
                break;
            case Calendar.SUNDAY:
                if(nameofday.equals("Sunday")){
                    ArrayList<medinfo> med_sunday = weekList.get(6).mMedItems;
                    Integer sun_length = med_sunday.size();
                    for(int i=0;i<sun_length;i++){
                        String med_sunday_time = med_sunday.get(i).getmTime();
                        if(med_sunday_time.equals(currentTime)){
                            CreateMedNotification(med_sunday.get(i).getmMedName(),position);
                            med_sunday.get(i).setNotificationShow(false);
                        }
                    }

                }
                break;

        }

    }
    @Override
    public int getItemCount() {
        return weekList.size();
    }

    private void CreateMedNotification(String item, int id) {

        NotificationManager notificationManager =(NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String description = "Med Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(mContext, MedicationSchedule.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Medication: " + item)
                .setContentText("It's time to take your medication!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(/*notification id*/id, notificationBuilder.build());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView weekdayname;
        RecyclerView childrecyclerview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekdayname = itemView.findViewById(R.id.weekdayheader);
            childrecyclerview = itemView.findViewById(R.id.childrecyclerview);
            mItemTouchHelper.attachToRecyclerView(childrecyclerview);

        }
    }
}