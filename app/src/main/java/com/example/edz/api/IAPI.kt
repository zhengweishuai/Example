package com.example.edz.api

import com.example.edz.bean.response.RegisterResponse
import com.example.edz.bean.response.WebsiteResponse
import com.network.INetResponse
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * author : zhengweishuai
 * date : 2020/6/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
interface IAPI {

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(@FieldMap maps: Map<String, String>): INetResponse<RegisterResponse>

    @GET("friend/json")
    suspend fun website(): INetResponse<WebsiteResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@FieldMap maps: Map<String, String>): INetResponse<Any>
}