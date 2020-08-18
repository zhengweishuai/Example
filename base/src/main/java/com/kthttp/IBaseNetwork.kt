package com.kthttp

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author : zhengweishuai
 * date : 2020/6/5 0005.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
interface INetworkRequest {
    /**
     * 创建okHttpClient
     */
    fun onCreateOkHttpClient(): OkHttpClient

    /**
     * 初始化OkHttp
     */
    fun okHttpBuild(builder: OkHttpClient.Builder): OkHttpClient.Builder

    /**
     * 初始化Retrofit
     */
    fun retrofitBuild(build: Retrofit.Builder): Retrofit.Builder
}