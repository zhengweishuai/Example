package com.example.edz.api

import com.example.edz.bean.BannerBean
import com.example.edz.bean.HomeArticleListResponse
import com.example.edz.bean.UserBean
import com.example.edz.bean.WebsiteResponse
import com.network.INetResponse
import retrofit2.http.*

/**
 * author : zhengweishuai
 * date : 2020/6/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
interface IAPI {

    //注册
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(@FieldMap maps: Map<String, String>): INetResponse<UserBean>

    @GET("friend/json")
    suspend fun website(): INetResponse<WebsiteResponse>

    //登录
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@FieldMap maps: Map<String, String>): INetResponse<UserBean>

    //首页的banner
    @GET("banner/json")
    suspend fun banners(): INetResponse<MutableList<BannerBean>>

    //广场列表数据
    @GET
    suspend fun homeArticleList(@Url url: String): INetResponse<HomeArticleListResponse>
}