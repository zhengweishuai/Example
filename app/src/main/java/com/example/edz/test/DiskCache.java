package com.example.edz.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * author : zhengweishuai
 * date : 2020/8/19 0019.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class DiskCache implements ImageCache {
    static String cacheDir = "sdcard/cache/";
    private LruCache<String, Bitmap> mCache;

    public DiskCache() {
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
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mCache.put(url, bitmap);
    }
}
