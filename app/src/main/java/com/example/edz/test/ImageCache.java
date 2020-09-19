package com.example.edz.test;

import android.graphics.Bitmap;

/**
 * author : zhengweishuai
 * date : 2020/8/19 0019.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
interface ImageCache {
    //获取
    Bitmap get(String url);

    //缓存
    void put(String url, Bitmap bitmap);
}
