/*
 * Copyright (c) 2021. HighCapable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("DEPRECATION", "MissingSuperCall")

package com.highcapable.sense

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.highcapable.sense.utils.createSense
import com.highcapable.sense.utils.findSense
import com.highcapable.sense.utils.removeSense

/**
 * [SenseActivity] is a [Sense] core container.
 * You can extends this Activity for customize.
 *
 * Create your Sense and use {@link startSense()} to start a Sense.
 *
 * Add this in your AndroidManifest.xml
 * <activity android:name="com.highcapable.sense.SenseActivity"/>
 */
open class SenseActivity : AppCompatActivity() {

    /**
     * The current [Sense] instance.
     * if this instance is null - You will got an error.
     */
    private val senseInstance get() = findSense(instanceTag)

    private var instanceTag = "" // The current instance tag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            /**
             * Create a [Sense] used className
             * Make sure this class is exist otherwise you will got a [ClassNotFoundException]
             */
            instanceTag = createSense(
                Class.forName(
                    intent?.getStringExtra("senseClassName")
                        ?: error("Missing Sense initialization")
                )
            )
            senseInstance?.onCreateContext(this)
            setContentView(FrameLayout(this).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                senseInstance?.doOnCreate(savedInstanceState)
                senseInstance?.bindRootLayoutWith(this)
            })
        } catch (e: Exception) {
            Log.e("Sense", "start Sense failed", e)
            Toast.makeText(
                this,
                "Try to start Sense failed! Please checking your className was right for this moment.\n" +
                        "The error has been printed to the console.",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        senseInstance?.doOnStart()
    }

    override fun onResume() {
        super.onResume()
        senseInstance?.doOnResume()
    }

    override fun onRestart() {
        super.onRestart()
        senseInstance?.doOnReStart()
    }

    override fun onPause() {
        super.onPause()
        senseInstance?.doOnPause()
    }

    override fun onStop() {
        super.onStop()
        senseInstance?.doOnStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        senseInstance?.doOnDestroy()
        removeSense(instanceTag)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        senseInstance?.onSenseResult(requestCode, resultCode, data)
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean, newConfig: Configuration?) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig)
        senseInstance?.onMultiWindowModeChanged(isInMultiWindowMode)
    }

    override fun onBackPressed() = senseInstance?.onBackPressed() ?: super.onBackPressed()
}