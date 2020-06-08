package com.example.edz.api;

import com.example.edz.constant.AppConfigs;
import com.network.BaseNetwork;

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
