package in.jvapps.system_alert_window;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class SystemAlertWindowService extends Service {
    private static final String CHANNEL_ID = "overlay_channel";

    @Override
    public void onCreate() {
        super.onCreate();
        startForegroundNotification();
    }

    private void startForegroundNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Overlay Foreground Service",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Overlay Running")
                .setContentText("Overlay is active in foreground")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setOngoing(true)
                .build();

        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Handle starting logic here if needed
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Handle cleanup here
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
