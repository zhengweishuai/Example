package com.example.edz.utils

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.edz.ui.fragment.GhostFragment

/**
 * author : zhengweishuai
 * date : 2020/7/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：Activity拓展
 */

/**
 * @param activity 当前activity
 * @param cls 目标 activity
 * @param extras 携带参数
 *
 */

private var sRequestCode = 0
    set(value) {
        field = if (value >= Integer.MAX_VALUE) 1 else value
    }

inline fun <reified T> AppCompatActivity.toActivity(vararg extras: Pair<String, Any?>) {
    this.startActivity(Intent(this, T::class.java).putExtraVararg(*extras))
}

/**
 * @param activity 当前activity
 * @param cls 目标 activity
 * @param extras 携带参数
 * @param callBack onActivityResult的回调
 */
fun AppCompatActivity.toActivityForResult(activity: AppCompatActivity,
                                          cls: Class<out AppCompatActivity>,
                                          vararg extras: Pair<String, Any?>,
                                          callBack: (result: Intent) -> Unit) {
    val intent = Intent(activity, cls).putExtraVararg(*extras)
    val supportFragmentManager = activity.supportFragmentManager
    val mFragment = GhostFragment()
    mFragment.init(++sRequestCode, intent, callBack = {
        callBack(it)
        if (mFragment.isAdded) supportFragmentManager.beginTransaction().remove(mFragment).commitAllowingStateLoss()
    })
    supportFragmentManager.beginTransaction().add(mFragment, GhostFragment::class.java.simpleName).commitAllowingStateLoss()
}

/**
 * 回调onActivityResult的关闭页面
 * @param extras 回调参数
 */
fun AppCompatActivity.finish(vararg extras: Pair<String, Any?>) {
    setResult(Activity.RESULT_OK, Intent().putExtraVararg(*extras))
    finish()
}