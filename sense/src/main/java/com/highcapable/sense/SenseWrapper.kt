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

package com.highcapable.sense

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

/**
 * [SenseWrapper] is the [Sense] based framework
 * This framework achieved [AppCompatActivity] and [FragmentManager]
 */
abstract class SenseWrapper {

    /**
     * The current [AppCompatActivity] instance
     */
    abstract val activity: AppCompatActivity?

    /**
     * The current [AppCompatActivity] with [FragmentManager] instance
     */
    abstract val supportFragmentManager: FragmentManager?

    /**
     * The current [AppCompatActivity] intent instance
     */
    abstract val intent: Intent?

    /**
     * The current [AppCompatActivity] resource instance
     */
    abstract val resources: Resources?

    /**
     * The current [AppCompatActivity] contentView instance
     */
    abstract val contentView: View?

    /**
     * The Current [Context] to init the [Sense] start
     * @param activity with an [AppCompatActivity] to init it
     */
    abstract fun onCreateContext(activity: AppCompatActivity)

    /**
     * When the [AppCompatActivity] setContentView event called
     * @param resId Layout resId
     */
    abstract fun addView(resId: Int)

    /**
     * When the [AppCompatActivity] setContentView event called
     * @param rootView Your custom View
     */
    abstract fun addView(rootView: View)

    /**
     * When the [AppCompatActivity] onCreate Lifecycle begins
     * @param savedInstanceState call to an Activity
     */
    abstract fun doOnCreate(savedInstanceState: Bundle?)

    /**
     * When the [AppCompatActivity] onResume Lifecycle begins
     */
    abstract fun doOnResume()

    /**
     * When the [AppCompatActivity] onStart Lifecycle begins
     */
    abstract fun doOnStart()

    /**
     * When the [AppCompatActivity] onReStart Lifecycle begins
     */
    abstract fun doOnReStart()

    /**
     * When the [AppCompatActivity] onPause Lifecycle begins
     */
    abstract fun doOnPause()

    /**
     * When the [AppCompatActivity] onStop Lifecycle begins
     */
    abstract fun doOnStop()

    /**
     * When the [AppCompatActivity] onDestroy Lifecycle begins
     */
    abstract fun doOnDestroy()

    /**
     * When the [AppCompatActivity] onBackPressed event called
     */
    abstract fun onBackPressed(): Unit?

    /**
     * When the [AppCompatActivity] onMultiWindowModeChanged Lifecycle begins
     */
    abstract fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean)

    /**
     * When the [AppCompatActivity] onActivityResult Lifecycle begins
     */
    abstract fun onSenseResult(requestCode: Int, resultCode: Int, data: Intent?)

    /**
     * When the [AppCompatActivity] finish event called
     */
    abstract fun finish(): Unit?
}