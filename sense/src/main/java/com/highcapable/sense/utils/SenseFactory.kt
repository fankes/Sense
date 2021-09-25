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

@file:Suppress("CAST_NEVER_SUCCEEDS", "unused")

package com.highcapable.sense.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.highcapable.sense.Sense

/**
 * The all [Sense] Array in your Application lifecycle.
 * Don't change this variable anywhere
 */
private val senseInstances = HashMap<String, Sense>()

/**
 * Create a [Sense] instance
 * @param cls Your Sense instance class
 * @return Sense Tag
 */
fun Context.createSense(cls: Class<*>): String {
    if (this !is AppCompatActivity) error("Sense must create with AppCompatActivity")
    val tagName = cls.name + "@" + System.currentTimeMillis()
    senseInstances[tagName] =
        cls.getConstructor().newInstance() as? Sense ?: error("Class not a Sense instance")
    return tagName
}

/**
 * Find a [Sense] in your Application lifecycle.
 * @param tag Your sense tag
 */
fun findSense(tag: String) = senseInstances[tag]

/**
 * Remove a [Sense] in your Application lifecycle.
 * @param tag Your sense tag
 */
fun removeSense(tag: String) = senseInstances.remove(tag)

/**
 * Check the [Sense] in your Application lifecycle is Repeating.
 * @param instanceClass Your sense class full name
 */
fun isSenseRepeating(instanceClass: String): Boolean {
    var isRepeat = false
    if (senseInstances.isNotEmpty())
        senseInstances.forEach { (t, _) ->
            if (t.contains(instanceClass)) {
                isRepeat = true
                return@forEach
            }
        }
    return isRepeat
}

/**
 * Removed [Sense] in your Application lifecycle when is Repeating.
 * @param instanceClass Your sense class full name
 */
fun removeRepeatingSense(instanceClass: String) {
    var senseInstance: Sense? = null
    var senseTag: String? = null
    if (senseInstances.isNotEmpty())
        senseInstances.forEach { (t, e) ->
            if (t.contains(instanceClass)) {
                senseTag = t
                senseInstance = e
                return@forEach
            }
        }
    senseInstance?.finish()
    senseTag?.let { removeSense(it) }
}