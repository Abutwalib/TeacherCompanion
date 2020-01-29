package com.example.teachercompanion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Get id and message
        int notificationId=intent.getIntExtra("notificationId",0);
        String subject=intent.getStringExtra("SUBJECT");
        String form =intent.getStringExtra("CLASS");
        String mess="Please Proceed to"+form+" For "+subject;
        //when notification is tapped call main activity
        Intent mainIntent=new Intent(context,ReminderAdd.class);
        PendingIntent contentIntent=PendingIntent.getActivity(context,0,mainIntent,0);
        NotificationManager myNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Prepare Notification
        Notification.Builder builder=new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Class Reminder!!!")
                .setContentText(mess)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL);



        //Notify
        myNotificationManager.notify(notificationId,builder.build());


    }
}
