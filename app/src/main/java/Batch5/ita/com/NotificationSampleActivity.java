package Batch5.ita.com;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class NotificationSampleActivity extends AppCompatActivity {

    private PendingIntent pIntent;
    private NotificationManager manager;
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    private Notification notification;
    Notification.Builder mBuilder;
    NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sample);
    }

    public void OnShowNotificationClicked(View view) {

        Intent intent = new Intent(NotificationSampleActivity.this, week4Activity.class);
        pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);


        String replyLabel = getResources().getString(R.string.reply_label);
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        Notification.Action action =
                new Notification.Action.Builder(android.R.drawable.stat_notify_more,
                        getString(R.string.label), pIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        Uri uriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        notification = new Notification.Builder(this)
                .setContentTitle("New Email arrived")
                .setContentText("wardah9658@gmail.com ...")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setProgress(100, 50, true)
                .setLights(Color.BLUE, 2000, 2000)
                .setNumber(1000)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.user_big))
                .addAction(action)
                .setSubText("this is sub massage !")
                .setSound(uriSound)
                .build();

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);


    }

    public void OnCancelNotificationClicked(View view) {


        mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(this);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(android.R.drawable.stat_sys_download);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        for (incr = 0; incr <= 100; incr += 5) {

                            mBuilder.setProgress(100, incr, false);
                            mNotifyManager.notify(0, mBuilder.build());
                            try {

                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                Log.d("sleepThread", "sleep failure");
                            }
                        }

                        mBuilder.setContentText("Download complete")
                                .setSmallIcon(android.R.drawable.stat_sys_download_done)
                                .setProgress(0, 0, false);
                        mNotifyManager.notify(2, mBuilder.build());
                    }
                }
        ).start();

    }

    public void OnCallNotificationClicked(View view) {

        Intent intent5 = new Intent();
        intent5.setAction(Intent.ACTION_CALL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);

        } else {
            try {
                intent5.setData(Uri.parse("tel:96714310"));
                startActivity(intent5);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
            }
        }

        pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent5, 0);

        notification = new Notification.Builder(this)
                .setContentTitle("Calling")
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_menu_call)
                .addAction(android.R.drawable.ic_menu_call,"try a call",pIntent)
                .build();

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(4, notification);
    }
}
