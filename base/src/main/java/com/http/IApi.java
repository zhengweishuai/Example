package com.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * created by zhengweis on 2019/8/13.
 * description：
 */
public interface IApi {
    /**
     * 获取发现页面-推荐读者
     */
    @GET("api/Found/GetRecommendAuthors")
    Observable<BaseResponse<Object>> getRecommendAuthors(@Query("user_id") int user_id,
                                                         @Query("pageIndex") int pageIndex,
                                                         @Query("pageSize") int pageSize,
                                                         @Query("sign") String sign);
}
