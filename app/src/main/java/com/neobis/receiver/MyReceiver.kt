package com.neobis.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            MY_OWN_ACTION -> {
                val quantity = intent.getIntExtra(counter, 0)
                Toast.makeText(context, "Clicked $quantity times", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "Подключитесь к зарядному утсройству", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    companion object {
        const val MY_OWN_ACTION = "clicked"
        const val counter = "counter"
    }
}