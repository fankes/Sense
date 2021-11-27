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

@file:Suppress("unused")

package com.highcapable.sense.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.highcapable.sense.Sense
import com.highcapable.sense.SenseActivity

/**
 * Start a [Sense]
 * You can start a Sense from another [Sense]
 * @param cls Your Sense class like startSense`<`YourClassName`>`()
 * @param bundle The param that you need to send for another Sense
 */
inline fun <reified cls : Sense> Sense.startSense(bundle: Bundle = Bundle()) =
    activity?.startSense(cls::class.java, bundle)

/**
 * Start a [Sense]
 * You can start a Sense from an [Activity]
 * @param cls Your Sense class like startSense`<`YourClassName`>`()
 * @param bundle The param that you need to send for another Sense
 */
inline fun <reified cls : Sense> Context.startSense(bundle: Bundle = Bundle()) =
    startSense(cls::class.java, bundle)

/**
 * Start a [Sense]
 * You can start a Sense from another [Sense]
 * @param cls Your Sense class like YourClassName::class.java
 * @param bundle The param that you need to send for another Sense
 */
fun Sense.startSense(cls: Class<*>, bundle: Bundle = Bundle()) = activity?.startSense(cls, bundle)

/**
 * Start a [Sense]
 * You can start a Sense from an [Activity]
 * @param cls Your Sense class like YourClassName::class.java
 * @param bundle The param that you need to send for another Sense
 */
fun Context.startSense(cls: Class<*>, bundle: Bundle = Bundle()) {
    if (this !is Activity) error("Sense must start with an Activity")
    cls.getConstructor().newInstance().let {
        if (it !is Sense) error("You must make sure the param \"cls\" is a Sense class")
        try {
            startActivity(Intent(this, SenseActivity::class.java)
                .apply {
                    putExtra("senseClassName", cls.name)
                    putExtras(bundle)
                })
        } catch (_: ActivityNotFoundException) {
            error("You must add the \"com.highcapable.sense.SenseActivity\" into your AndroidManifest.xml first")
        }
    }
}

/**
 * Start a [Sense]
 * You can start a Sense from another [Sense]
 * @param cls Your Sense class like YourClassName::class.java
 * @param requestCode The Current Sense requestCode
 * @param bundle The param that you need to send for another Sense
 */
fun Sense.startSenseForResult(cls: Class<*>, requestCode: Int, bundle: Bundle = Bundle()) =
    activity?.startSenseForResult(cls, requestCode, bundle)

/**
 * Start a [Sense]
 * You can start a Sense from an [Activity]
 * @param cls Your Sense class like YourClassName::class.java
 * @param requestCode The Current Sense requestCode
 * @param bundle The param that you need to send for another Sense
 */
fun Context.startSenseForResult(cls: Class<*>, requestCode: Int, bundle: Bundle = Bundle()) {
    if (this !is Activity) error("Sense must start with an Activity")
    cls.getConstructor().newInstance().let {
        if (it !is Sense) error("You must make sure the param \"cls\" is a Sense class")
        try {
            startActivityForResult(
                Intent(this, SenseActivity::class.java)
                    .apply {
                        putExtra("senseClassName", cls.name)
                        putExtras(bundle)
                    }, requestCode
            )
        } catch (_: ActivityNotFoundException) {
            error("You must add the \"com.highcapable.sense.SenseActivity\" into your AndroidManifest.xml first")
        }
    }
}