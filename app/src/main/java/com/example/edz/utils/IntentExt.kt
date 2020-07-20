package com.example.edz.utils

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * author : zhengweishuai
 * date : 2020/7/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：Intent拓展
 */
fun Intent.putExtraVararg(
        vararg extras: Pair<String, Any?>
): Intent {
    if (extras.isEmpty()) return this
    extras.forEach { (key, value) ->
        when (value) {
            is Bundle -> this.putExtra(key, value)
            is Boolean -> this.putExtra(key, value)
            is BooleanArray -> this.putExtra(key, value)
            is Byte -> this.putExtra(key, value)
            is ByteArray -> this.putExtra(key, value)
            is Char -> this.putExtra(key, value)
            is CharArray -> this.putExtra(key, value)
            is String -> this.putExtra(key, value)
            is CharSequence -> this.putExtra(key, value)
            is Double -> this.putExtra(key, value)
            is DoubleArray -> this.putExtra(key, value)
            is Float -> this.putExtra(key, value)
            is FloatArray -> this.putExtra(key, value)
            is Int -> this.putExtra(key, value)
            is IntArray -> this.putExtra(key, value)
            is Long -> this.putExtra(key, value)
            is LongArray -> this.putExtra(key, value)
            is Short -> this.putExtra(key, value)
            is ShortArray -> this.putExtra(key, value)
            is Array<*> -> {
                @Suppress("UNCHECKED_CAST")
                when {
                    value.isArrayOf<String>() -> {
                        this.putStringArrayListExtra(key, value as ArrayList<String?>)
                    }
                    value.isArrayOf<CharSequence>() -> {
                        this.putCharSequenceArrayListExtra(key, value as ArrayList<CharSequence?>)
                    }
                    value.isArrayOf<Parcelable>() -> {
                        this.putParcelableArrayListExtra(key, value as ArrayList<out Parcelable?>)
                    }
                }
            }
            is Parcelable -> this.putExtra(key, value)
            is Serializable -> putExtra(key, value)
            else -> {
                throw IllegalArgumentException("Not support $value type ${value?.javaClass}..")
            }
        }
    }
    return this
}
