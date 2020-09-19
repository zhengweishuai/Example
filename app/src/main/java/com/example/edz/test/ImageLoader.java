package com.example.edz.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author : zhengweishuai
 * date : 2020/8/19 0019.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
public class ImageLoader {
    private static ImageLoader loader;
    //图片缓存
    private ImageCache mImageCache;
    //线程池
    private ExecutorService mService;

    public static ImageLoader getInstance() {
        if (loader == null) {
            synchronized (ImageLoader.class) {
                if (loader == null) {
                    loader = new ImageLoader();
                }
            }
        }
        return loader;
    }

    private ImageLoader() {
        mImageCache = new MemoryCache();
        mService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /*
    设置缓存策略
     */
    public void setImageCache(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    /*
    加载bitmao
     */
    public void displayImage(ImageView imageView, String url) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            loadRequest(imageView, url);
        }
    }

    private void loadRequest(ImageView imageView, String url) {
        imageView.setTag(url);
        mService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap netBitmap = downloadImage(url);
                if (netBitmap != null) {
                    if (imageView.getTag().equals(url)) {
                        imageView.setImageBitmap(netBitmap);
                    }
                    mImageCache.put(url, netBitmap);
                }
            }
        });
    }

    /*
    网络获取bitmap
     */
    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
            URL mUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
