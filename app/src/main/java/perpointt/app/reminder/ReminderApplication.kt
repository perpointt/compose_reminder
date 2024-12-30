package perpointt.app.reminder

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.os.Build
import android.provider.Settings
import com.jakewharton.threetenabp.AndroidThreeTen


class ReminderApplication : Application() {
    companion object {
        const val CHANNEL_NAME = "Reminders"
        const val CHANNEL_DESCRIPTION = "Channel for reminders"
        const val CHANNEL_ID = "reminders"
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                    .apply {
                        description = CHANNEL_DESCRIPTION
                        setSound(
                            Settings.System.DEFAULT_NOTIFICATION_URI,
                            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION)
                                .build()
                        )
                    }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}