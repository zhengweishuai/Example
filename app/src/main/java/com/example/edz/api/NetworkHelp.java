package com.example.edz.api;

import com.example.edz.constant.AppConfigs;
import com.network.BaseNetworkRequest;

/**
 * author : zhengweishuai
 * date : 2020/6/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
public class NetworkHelp {
    private static IAPI help;

    public static IAPI newInstance() {
        if (help == null) {
            synchronized (NetworkHelp.class) {
                if (help == null) {
                    BaseNetworkRequest baseNetworkRequest = new BaseNetworkRequest();
                    help = baseNetworkRequest.init(IAPI.class, AppConfigs.base_url);
                }
            }
        }
        return help;
    }
}
