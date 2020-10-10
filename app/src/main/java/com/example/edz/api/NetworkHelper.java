package com.example.edz.api;

import com.constant.AppConfigs;
import com.kthttp.BaseNetwork;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author : zhengweishuai
 * date : 2020/6/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
public class NetworkHelper {
    private static IAPI help;

    public static IAPI newInstance() {
        if (help == null) {
            synchronized (NetworkHelper.class) {
                if (help == null) {
                    BaseNetwork baseNetwork = new BaseNetwork();
                    help = baseNetwork.init(IAPI.class, AppConfigs.base_url);
                }
            }
        }
        return help;
    }
}
