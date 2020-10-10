package com.http;

import android.content.Context;

import com.base.BuildConfig;
import com.constant.AppConfigs;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by zhengweis on 2019/8/13.
 * description：
 */
public class ApiHelper {
    static IApi iApi;

    private ApiHelper() {
        throw new AssertionError();
    }

    public static void init() {
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.IS_DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }
        int DEFAULT_TIME = 10;
        OkHttpClient okHttpClient = builder
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppConfigs.base_url)
                .build();
        iApi = retrofit.create(IApi.class);
    }

    public static void getRecommendAuthors(Context context, ApiCallBack<Object> observer) {
        iApi.getRecommendAuthors(100, 1, 5, "e7534d4511c7ae46f3d538347ef7a469")

                .subscribe(observer);
    }

}
