package com.example.edz.api

import android.provider.ContactsContract
import com.example.edz.bean.request.LoginRequest
import com.example.edz.bean.response.WebsiteResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * author : zhengweishuai
 * date : 2020/6/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
interface IAPI {
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Any

    @GET("friend/json")
    fun website(): WebsiteResponse
}