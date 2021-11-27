package com.highcapable.sense.example.ui.sense

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.highcapable.sense.Sense
import com.highcapable.sense.SenseActivity
import com.highcapable.sense.example.R
import com.highcapable.sense.utils.startSense

class MainSense : Sense() {

    override fun doOnCreate(savedInstanceState: Bundle?) {
        super.doOnCreate(savedInstanceState)
        /**
         * Add your RootLayout here.
         * 使用此方法添加根布局
         */
        addView(R.layout.sense_main)
        /**
         * The "activity" variable is called to the base [SenseActivity] instance.
         * We can use it easy like an Activity.
         * “activity” 实例变量是 [SenseActivity] 的实例对象，你可以方便的去调用片段绑定的 Activity。
         */
        activity?.title = "MainSense"
        /**
         * We can use this method easy like an Activity!!
         * Use findViewById<View>(resId) to find your View.
         * Sense 集成了 Activity 中的 FindById 方法，你可以轻松的使用它。
         */
        findViewById<Button>(R.id.sense_main_button).setOnClickListener {
            /**
             * We need use this method to start another Sense
             * If you want to send data for another Sense,You can use Bundle param.
             * 你可以在一个 Sense 中去启动另一个 Sense
             * 如果你需要传参，可以参考下面被注释的方法
             * 接收参数相当简单，因为 Sense 绑定 Activity，你只需要轻松调用 intent?.getXXX() 方法即可得到其中的参数。
             */
            startSense<AnotherSense>()
            /**
             * This is the second example
             * Use Bundle param to send the data.
             */
            /// startSense<AnotherSense>(
            ///     Bundle().apply { putString("simpleData", "something there") }
            /// )
        }
    }

    override fun onSenseResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onSenseResult(requestCode, resultCode, data)
        Log.d("MainSense", "onSenseResult: 你可以在这里轻松实现 onActivityResult 一样的效果")
    }
}