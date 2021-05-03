package com.example.android.bluetoothchat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    public static final String description = "Med Channel";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels(){
        NotificationChannel channel1 = new NotificationChannel(NOTIFICATION_CHANNEL_ID,description, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.design_default_color_primary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
    }

    public NotificationManager getManager(){
        if(mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotifications(String medname){
        return new NotificationCompat.Builder(getApplicationContext(),NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Medication Time!")
                .setContentText(medname + " is being dispensed")
                .setSmallIcon(R.drawable.ic_pill);

    }


}
