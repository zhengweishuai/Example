package com.example.edz.presenter;

import android.text.TextUtils;

import com.example.edz.contract.LoginContract;
import com.mvp.BasePresenter;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginUi> implements LoginContract.LoginPtr {

    public LoginPresenter(LoginContract.LoginUi view) {
        super(view);
    }

    @Override
    public void login(String username, String password) {
        if (!TextUtils.equals(username, "张三")) {
            getView().loginFaild("输入用户名不正确");
        } else if (!TextUtils.equals(password, "123456")) {
            getView().loginFaild("输入密码不正确");
        } else {
            getView().loginSuccess("登录成功");
        }
    }
}
