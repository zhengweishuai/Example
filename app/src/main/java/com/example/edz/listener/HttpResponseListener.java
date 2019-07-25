package com.example.edz.listener;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public interface HttpResponseListener {
    /**
     * 网络请求成功
     *
     * @param tag 请求的标记
     * @param t   返回的数据
     */
    void onSuccess(Object tag, String t);

    /**
     * 网络请求失败
     * @param tag     请求的标记
     * @param failure 请求失败时，返回的信息类
     */
    void onFailure(Object tag, String failure);
}
