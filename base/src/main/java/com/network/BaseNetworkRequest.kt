package com.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * author : zhengweishuai
 * date : 2020/6/5 0005.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
open class BaseNetworkRequest : INetworkRequest {


    //连接超时时间，单位s
    private val DEFAULT_CONNECT_TIMEOUT = 10L

    //读超时时间，单位s
    private val DEFAULT_READ_TIMEOUT = 10L

    //写超时时间，单位s
    private val DEFAULT_WRITE_TIMEOUT = 10L

    fun <T> init(clz: Class<T>, baseUrl: String): T {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HeadInterceptor(HttpHead.headMap))
                .addInterceptor(LogInterceptor()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .build()
        return retrofit.create(clz)
    }


    override fun onCreateOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpBuild(okHttpClient)
        return okHttpClient.build()
    }

    override fun okHttpBuild(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HeadInterceptor(HttpHead.headMap))
                .addInterceptor(LogInterceptor())
    }

    override fun retrofitBuild(build: Retrofit.Builder): Retrofit.Builder {
        return build
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }
}