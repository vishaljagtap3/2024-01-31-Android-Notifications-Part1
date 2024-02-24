package `in`.bitcode.notifications

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    /*private var btnNotify : Button? = null
    private var btnCancel : Button? = null*/
    private lateinit var btnNotify : Button
    private lateinit var btnCancel : Button

    //private lateinit var notificationManager : NotificationManager
    private lateinit var notificationManagerCompat : NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNotify = findViewById(R.id.btnNotify)
        btnCancel = findViewById(R.id.btnCancel)

        //notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManagerCompat = NotificationManagerCompat.from(this)
        createNotificationChannels()

        btnNotify.setOnClickListener(
            object : View.OnClickListener {
                @SuppressLint("NotificationPermission", "MissingPermission")
                override fun onClick(view: View?) {
                    //val builder = Notification.Builder(this@MainActivity);

                    val intent = Intent(this@MainActivity, UpdatesActivity::class.java)
                    val pendingIntent = PendingIntent.getActivity(
                        this@MainActivity,
                        1,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )

                    val builder = NotificationCompat.Builder(this@MainActivity, "batch_updates")
                    builder.setContentTitle("BitCode Batch Updates...")
                    builder.setContentText("Anushka will be reaching pune on Monday....")
                    builder.setSmallIcon(R.mipmap.ic_launcher)
                    builder.setColor(Color.RED)
                    builder.setAutoCancel(true)
                    builder.setChannelId("batch_updates")
                    builder.setContentIntent(pendingIntent)
                    val notification = builder.build()

                    notificationManagerCompat.notify(101, notification)
                }
            }
        )

        btnCancel.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    notificationManagerCompat.cancel(101)
                }
            }
        )
    }

    private fun createNotificationChannels() {
        val builder =
            NotificationChannelCompat.Builder("batch_updates", NotificationManagerCompat.IMPORTANCE_HIGH)
        builder.setName("Batch Updates")
        builder.setDescription("Receive notifications about batch updates.")
        notificationManagerCompat.createNotificationChannel(builder.build())

        val builder1 = NotificationChannelCompat.Builder("marketing", NotificationManagerCompat.IMPORTANCE_DEFAULT)
        builder1.setName("Marketing")
        builder1.setDescription("Receive offers")
        notificationManagerCompat.createNotificationChannel(builder1.build())
    }
}









