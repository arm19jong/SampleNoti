package com.jongzazaal.samplenoti.notification

import android.app.Notification
import com.google.firebase.messaging.FirebaseMessagingService
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import com.jongzazaal.samplenoti.MainActivity
import com.google.firebase.messaging.RemoteMessage
import com.jongzazaal.samplenoti.R
import com.jongzazaal.samplenoti.TwoActivity
import java.io.IOException
import java.net.URL
import android.os.SystemClock



class MyFirebaseMessagingService: FirebaseMessagingService() {
    lateinit var intent:Intent
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        val notification = remoteMessage?.notification
        val data = NotificationData(remoteMessage!!.data)
        Log.d("tag2", "debug")
        sendNotification(notification,data)
    }

    /**
     * Create and show a custom notification containing the received FCM message.
     *
     * @param notification FCM notification payload received.
     * @param data FCM data payload received.
     */
    private fun sendNotification(notification: RemoteMessage.Notification?, data: NotificationData) {
        val icon = BitmapFactory.decodeResource(resources, R.drawable.icon_app)

        val backIntent = Intent(this, MainActivity::class.java)
        backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        when(data.click){
            "TWO_ACTIVITY" -> {
                intent =  Intent(this, TwoActivity::class.java)
                intent.putExtra("extra", data.extra)
            }

            else -> {
                intent =  Intent(this, MainActivity::class.java)
            }
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivities(this, 0, arrayOf(backIntent, intent), PendingIntent.FLAG_ONE_SHOT)
//
        val notificationBuilder = NotificationCompat.Builder(this, data.channel_id)
                .setContentTitle(data.title)
                .setContentText(data.des)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
//                .setContentInfo(notification.title)
                .setLights(Color.RED, 1000, 300)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setLargeIcon(icon)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            notificationBuilder.setSmallIcon(R.drawable.icon_app_three)
        }
        else{
            notificationBuilder.setSmallIcon(R.drawable.icon_app_two)
        }

        try {
            val picture_url = data.picture_url
            if (picture_url != null && "" != picture_url) {
                val url = URL(picture_url)
                val bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//                notificationBuilder.setStyle(
//                        NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setSummaryText("des2")
//                )
                notificationBuilder.setLargeIcon(bigPicture)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification Channel is required for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    data.channel_id, data.channel_name, NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = data.channel_des
            channel.setShowBadge(true)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500)
            notificationManager.createNotificationChannel(channel)
        }
        val oneTimeID = SystemClock.uptimeMillis().toInt()
        notificationManager.notify(oneTimeID, notificationBuilder.build())
    }

}