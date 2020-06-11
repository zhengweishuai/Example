package com.example.edz.utils

import android.content.Context
import android.widget.Scroller


/**
 * author : zhengweishuai
 * date : 2020/6/11 0011.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class FixedSpeedScroller(context: Context) : Scroller(context) {
    private var mDuration = 1000

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    fun setmDuration(time: Int) {
        mDuration = time
    }

    fun getmDuration(): Int {
        return mDuration
    }
}