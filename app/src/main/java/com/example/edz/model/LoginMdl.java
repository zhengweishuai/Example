package com.example.edz.model;

import android.text.TextUtils;

import com.example.edz.contract.LoginContract;
import com.example.edz.listener.HttpResponseListener;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public class LoginMdl implements LoginContract.LoginMdl {
    @Override
    public void login(String username, String password, HttpResponseListener callbak) {
        if (!TextUtils.equals(username, "张三")) {
            callbak.onFailure(-1, "输入用户名不正确");
        } else if (!TextUtils.equals(password, "123456")) {
            callbak.onFailure(-1, "输入密码不正确");
        } else {
            callbak.onSuccess(0, "登录成功");
        }
    }
}
