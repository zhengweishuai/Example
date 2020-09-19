package com.example.edz.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.edz.test.ImageLoader;

/**
 * author : zhengweishuai
 * date : 2020/8/19 0019.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoader.getInstance().displayImage(null, "");
    }
}
