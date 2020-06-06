package com.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * author : zhengweishuai
 * date : 2020/6/5 0005.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：网络请求头部拦截
 */
class HeadInterceptor(var heads: MutableMap<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val keys = heads.keys
        for (k in keys) {
            heads[k]?.run {
                request.addHeader(k, this)
            }
        }
        return chain.proceed(request.build())
    }
}