package com.cbiu.firebasenotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // title
        // Message called as body

        // this if body code written for execute notification wlhen app is in foregound
       if (remoteMessage.getData().isEmpty())
       {
           showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
       }
       // this if body code written for execute notification wlhen app is in background;
       else
       {
           showNotification(remoteMessage.getData());
       }

        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    // this method will be used for reeive notification in when app is in backgroud
    private void showNotification(Map<String, String> data) {

        String title = data.get("title");
        String body = data.get("body");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "cbiu notification for student";
        // this below sdk checked is used for check user device is android sdk orio or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"NOTIFICATION", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("CBIU APP");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setContentInfo("Info");
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round);

        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());

    }

    // this method will be used for reeive notification in when app is in forgound
    private void showNotification(String title, String body) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "cbiu notification for student";
        // this below sdk checked is used for check user device is android sdk orio or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"NOTIFICATION", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("CBIU APP");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setContentInfo("Info");
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round);

        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }
}
