package com.example.edz.pattern.factory.A;

import com.utils.LogUtil;

/**
 * created by zhengweis on 2019/10/15.
 * description：具体对象
 */
public class MeizuPhone implements Phone {
    @Override
    public void callPhone(int phone) {
        LogUtil.d("魅族拨打的号码" + phone);
    }
}
