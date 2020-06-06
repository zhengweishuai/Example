package com.network

import java.io.Serializable

/**
 * author : zhengweishuai
 * date : 2020/6/5 0005.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：网络请求头部
 */
object HttpHead : Serializable {
    var headMap = mutableMapOf<String, String>()

    /*
    添加元素
     */
    fun put(key: String, value: String) {
        headMap[key] = value
    }

    /*
    查询value
     */
    fun get(key: String) {
        headMap[key]
    }

    /*
     移除元素
     */
    fun remove(key: String) {
        headMap.remove(key)
    }

    /*
    清空
     */
    fun clear(key: String) {
        headMap.clear()
    }
}