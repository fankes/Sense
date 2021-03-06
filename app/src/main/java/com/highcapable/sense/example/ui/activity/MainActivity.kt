package com.highcapable.sense.example.ui.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.highcapable.sense.example.R
import com.highcapable.sense.example.ui.sense.MainSense
import com.highcapable.sense.utils.startSense

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.activity_start_button).setOnClickListener {
            /**
             * We need use this method to start a Sense
             * 我们只需要使用此方法启动一个 Sense
             * ------
             * 1.1 版本提供了一种新的方式启动 Sense
             * 使用新的泛型方法 [startSense] 来启动目标 [MainSense]
             */
            // startSense(MainSense::class.java)
            startSense<MainSense>()
        }
    }
}