package com.cs407.lab7;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

public class ReplyReceiver extends BroadcastReceiver {
    /*public void showNotification(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
        != PackageManager.PERMISSION_GRANTED)) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setContentTitle(sender)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationCompat notificationCompat = NotificationCompat.from(context);
        notificationCompat.notify(notificationID, builder.build());
    }*/
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        int id = intent.getIntExtra("id", -1);

        if (remoteInput != null) {
            CharSequence charSequence = remoteInput.getCharSequence(NotificationHelper.TEXT_REPLY);
            if (charSequence == null) return;

            Toast.makeText(context, context.getString(R.string.replied, charSequence.toString(), id), Toast.LENGTH_LONG).show();
            NotificationHelper.getInstance().showNotification(context, id);
        }
    }
}
