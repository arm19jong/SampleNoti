package com.jongzazaal.samplenoti

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var topic: String = "alls"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sub.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic(topic)
        }

        unSubAll.setOnClickListener {
            Thread(Runnable {
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }).start()
        }
    }
}
