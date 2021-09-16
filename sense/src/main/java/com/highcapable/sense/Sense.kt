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

package com.highcapable.sense

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * [Sense] is a chip,It must extends an Activity
 * It has the complete life cycle of the Activity and is used in conjunction with the current [AppCompatActivity]
 */
open class Sense : SenseWrapper() {

    companion object {

        private const val ERROR_ADDVIEW = "RootLayout must not be null or an container layout"
        private const val ERROR_CONTAINER =
            "Illegal View type! RootLayout must be an container layout"
        private const val ERROR_CONTAINER_NO_INIT =
            "You must call addView(Int|View) to initialization the RootLayout in doOnCreate(Bundle) method"
        private const val ERROR_FINDVIEWBYID =
            "Before use findViewById(resId) You must call addView(resId) first"
    }

    private var rootView: ViewGroup? = null // The RootLayout for Sense.
    private var nSelf: AppCompatActivity? = null // The BaseContext for Sense.

    override val activity get() = nSelf

    override val contentView get() = rootView

    override val resources get() = nSelf?.resources

    override val intent get() = nSelf?.intent

    override val supportFragmentManager get() = nSelf?.supportFragmentManager

    override fun onCreateContext(activity: AppCompatActivity) {
        nSelf = activity
    }

    override fun doOnCreate(savedInstanceState: Bundle?) {}

    override fun doOnResume() {}

    override fun doOnStart() {}

    override fun doOnReStart() {}

    override fun doOnPause() {}

    override fun doOnStop() {}

    override fun doOnDestroy() {}

    override fun onBackPressed() = finish()

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {}

    override fun onSenseResult(requestCode: Int, resultCode: Int, data: Intent?) {}

    override fun finish() = nSelf?.finish()

    override fun addView(resId: Int) {
        rootView = LayoutInflater.from(nSelf).inflate(resId, null) as? ViewGroup
            ?: error(ERROR_ADDVIEW)
    }

    override fun addView(rootView: View) {
        if (rootView !is ViewGroup) error(ERROR_ADDVIEW)
        this.rootView = rootView
    }

    /**
     * [Sense] used findViewById like an Activity
     * @param resId Your view id
     * @return view
     */
    fun <view : View> findViewById(resId: Int) =
        contentView?.findViewById<view>(resId) ?: error(ERROR_FINDVIEWBYID)

    /**
     * Use this to find a View type
     * @param resId Your view id
     * @return View
     */
    fun findView(resId: Int) =
        contentView?.findViewById<View>(resId) ?: error(ERROR_FINDVIEWBYID)

    /**
     * [Sense] must bind with an [AppCompatActivity] root layout to init it
     * @param viewId Your container view Id. for example: [FrameLayout]
     */
    fun bindRootLayoutWith(viewId: Int) {
        rootView ?: error(ERROR_CONTAINER_NO_INIT)
        nSelf?.findViewById<View>(viewId)?.let {
            if (it !is ViewGroup) error(javaClass.simpleName + " InitFail:" + ERROR_CONTAINER)
            it.addView(
                rootView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    /**
     * [Sense] must bind with an [AppCompatActivity] root layout to init it
     * @param view Your container view instance. for example: [FrameLayout]
     */
    fun bindRootLayoutWith(view: View) {
        rootView ?: error(ERROR_CONTAINER_NO_INIT)
        view.let {
            if (it !is ViewGroup) error(javaClass.simpleName + " InitFail:" + ERROR_CONTAINER)
            it.addView(
                rootView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }
}