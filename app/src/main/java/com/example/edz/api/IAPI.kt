package com.example.edz.api

import com.example.edz.bean.*
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
    suspend fun banners(): INetResponse<ArrayList<BannerBean>>

    //广场列表数据
    @GET
    suspend fun homeArticleList(@Url url: String): INetResponse<HomeArticleListResponse>

    //公众号列表
    @GET("wxarticle/chapters/json")
    suspend fun wxarticle(): INetResponse<ArrayList<ArticleAuthorBean>>

    //公众号下的文章列表
    @GET
    suspend fun wxArticleHistory(@Url url: String): INetResponse<HomeArticleListResponse>

    //项目列表数据
    @GET("project/list/1/json?cid=294")
    suspend fun projectList(): INetResponse<ProjectListResponse>

    //收藏站内文章
    @POST
    suspend fun collectArticle(@Url url: String): INetResponse<Any>

    //收藏站内文章
    @GET
    suspend fun collectArticleList(@Url url: String): INetResponse<HomeArticleListResponse>
}