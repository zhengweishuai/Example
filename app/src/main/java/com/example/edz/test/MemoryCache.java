package com.example.edz.test;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * author : zhengweishuai
 * date : 2020/8/19 0019.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mCache;

    public MemoryCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024 / 4);
        mCache = new LruCache<String, Bitmap>(maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
