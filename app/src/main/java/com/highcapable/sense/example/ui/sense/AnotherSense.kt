package com.highcapable.sense.example.ui.sense

import android.os.Bundle
import com.highcapable.sense.Sense
import com.highcapable.sense.example.R

class AnotherSense : Sense() {

    override fun doOnCreate(savedInstanceState: Bundle?) {
        super.doOnCreate(savedInstanceState)
        addView(R.layout.sense_another)
        activity?.title = "AnotherSense"
        findView(R.id.sense_another_button).setOnClickListener { onBackPressed() }
    }
}