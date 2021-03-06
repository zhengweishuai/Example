package com.kthttp

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * author : zhengweishuai
 * date : 2020/6/5 0005.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：日志拦截
 */
class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        LogUtils.d(request.toString())
        val response = chain.proceed(chain.request())
        val content = response.body()?.string()
        LogUtils.json(content)
        val mediaType = response.body()!!.contentType()
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content ?: ""))
                .build()
    }
}