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
    @GET("article/list/{pageNum}/json")
    suspend fun homeArticleList(@Path("pageNum") pageNum: Int): INetResponse<HomeArticleListResponse>

    //公众号列表
    @GET("wxarticle/chapters/json")
    suspend fun wxarticle(): INetResponse<ArrayList<ArticleAuthorBean>>

    //公众号下的文章列表
    @GET("wxarticle/list/{id}/{pageNum}/json")
    suspend fun wxArticleHistory(@Path("id") id: Int,
                                 @Path("pageNum") pageNum: Int): INetResponse<HomeArticleListResponse>

    //项目分类
    @GET("project/tree/json")
    suspend fun projectCategory(): INetResponse<ArrayList<ProjectCategoryBean>>

    //项目列表数据
    @GET("project/list/{pageNum}/json")
    suspend fun projectList(@Path("pageNum") pageNum: Int,
                            @Query("cid") cid: Int): INetResponse<BaseListResponse<ProjectDetailBean>>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): INetResponse<Any>

    //我的收藏
    @GET("lg/collect/list/{pageNum}/json")
    suspend fun collectArticleList(@Path("pageNum") pageNum: Int): INetResponse<HomeArticleListResponse>

    //取消收藏
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int): INetResponse<Any>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int,
                                     @Query("originId") originId: Int): INetResponse<Any>

    /**
     * 体系
     */
    @GET("tree/json")
    suspend fun tree(): INetResponse<ArrayList<SeriesCollectBean>>

    /**
     * 知识体系下的文章
     */
    @GET("article/list/{pageNum}/json")
    suspend fun treeList(@Path("pageNum") pageNum: Int,
                     @Query("cid") cid: Int): INetResponse<HomeArticleListResponse>
}