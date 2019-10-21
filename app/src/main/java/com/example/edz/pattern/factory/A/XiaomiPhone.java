package com.example.edz.pattern.factory.A;

import com.utils.LogUtil;

/**
 * created by zhengweis on 2019/10/15.
 * description：
 */
public class XiaomiPhone implements Phone {
    @Override
    public void callPhone(int phone) {
        LogUtil.d("小米拨打的号码" + phone);
    }
}
