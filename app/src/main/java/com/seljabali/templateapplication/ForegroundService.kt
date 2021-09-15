package com.seljabali.templateapplication

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.IBinder
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import com.seljabali.core.utilities.NotificationChannelGroups
import com.seljabali.core.utilities.NotificationChannels
import com.seljabali.core.utilities.OsVersionUtil
import com.seljabali.core.utilities.OsVersions
import com.seljabali.network.MetaWeatherService
import com.seljabali.templateapplication.ui.HomeActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.observable.ObservableInterval
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class ForegroundService : Service() {

    companion object {
        private val TAG: String = ForegroundService::class.java.name
        private const val SERVICE_ID = 123
        private const val CALL_API_INTERVAL_SECONDS = 10L
    }

    private val compositeDisposable = CompositeDisposable()
    private val api: MetaWeatherService by inject()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service started")
//        if (intent?.action?.equals(ACTION_STOP_FOREGROUND, ignoreCase = true) == true) {
//            stopForeground(true)
//            stopSelf()
//        }
        generateForegroundNotification()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "Service onBind")
        return null
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d(TAG, "Service has been closed")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service has been created")
        compositeDisposable.add(
            ObservableInterval.interval(CALL_API_INTERVAL_SECONDS, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext {
                    compositeDisposable.add(api.getWeatherForWhereOnEarthId(2487956)
                        .doOnError { error ->
                            Log.e(TAG, error.message ?: "Failed to do api call")
                        }
                        .doOnNext { response ->
                            Log.d(TAG, "${response.cityTitle}")
                        }
                        .subscribe()
                    )
                }
                .subscribe()
        )
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d(TAG, "Service has been stopped")
        return super.stopService(name)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "Service has been closed")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "Service onDestroy")
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun startServiceInForeground(notification: Notification) {
        startForeground(SERVICE_ID, notification)
    }

    private fun stopServiceInForeground() {
        stopForeground(true)
        stopSelf()
    }

    @SuppressLint("NewApi")
    private fun generateForegroundNotification() {
        val intentMainLanding = Intent(this, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intentMainLanding, 0)
        if (OsVersionUtil.isAtLeast(OsVersions.O_11)) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannelGroup(
                NotificationChannelGroups.CHAT.newInstance(this)
            )
            val notificationChannel = NotificationChannels.CHAT.newInstance(this)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notification = getNotification(pendingIntent)
        startServiceInForeground(notification)
    }

    private fun getNotification(pendingIntent: PendingIntent): Notification {
        val iconNotification = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val builder = NotificationCompat.Builder(this, "service_channel").apply {
            setContentTitle(
                StringBuilder(resources.getString(R.string.app_name))
                    .append(" service is running")
                    .toString()
            )
            setTicker(
                StringBuilder(resources.getString(R.string.app_name))
                    .append("service is running")
                    .toString()
            )
            setContentText("Touch to open")
            setSmallIcon(R.drawable.ic_apartments)
            priority = NotificationCompat.PRIORITY_LOW
            setWhen(0)
            setOnlyAlertOnce(true)
            setContentIntent(pendingIntent)
            setOngoing(true)
            if (iconNotification != null) {
                setLargeIcon(Bitmap.createScaledBitmap(iconNotification, 128, 128, false))
            }
            color = resources.getColor(R.color.purple)
        }
        return builder.build()
    }
}