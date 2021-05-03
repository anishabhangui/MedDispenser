package com.example.android.bluetoothchat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import static com.example.android.bluetoothchat.AddMed.mednamestring;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotifications(mednamestring);
        notificationHelper.getManager().notify(1,nb.build());
    }
}
