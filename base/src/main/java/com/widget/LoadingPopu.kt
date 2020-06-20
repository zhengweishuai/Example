package com.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import com.base.R
import com.lxj.xpopup.impl.FullScreenPopupView
import kotlinx.android.synthetic.main.layout_base_loading.view.*

/**
 * author : zhengweishuai
 * date : 2020/6/19 0019.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class LoadingPopu(context: Context) : FullScreenPopupView(context) {
    override fun getImplLayoutId(): Int = R.layout.layout_base_loading

    @SuppressLint("WrongConstant")
    override fun onCreate() {
        super.onCreate()
        val rotateAnimator = ObjectAnimator.ofFloat(loading, "rotation", 0f, 360f)
        rotateAnimator.duration = 1000
        rotateAnimator.repeatMode = ValueAnimator.INFINITE
        rotateAnimator.repeatCount = ValueAnimator.INFINITE
        rotateAnimator.start()
    }
}