package com.neobis.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "loaded") {
                val percent = intent.getIntExtra("percent", 0)
                Toast.makeText(context, "Loaded $percent", Toast.LENGTH_SHORT).show()
                progressBar.setProgress(percent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        var counter = 0
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(MyReceiver.MY_OWN_ACTION).putExtra(MyReceiver.counter, ++counter)
            localBroadcastManager.sendBroadcast(intent)
        }

        val intentFilter = IntentFilter().apply {
            addAction("loaded")
        }
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
        localBroadcastManager.registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(receiver)
    }
}