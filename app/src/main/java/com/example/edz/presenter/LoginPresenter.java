package com.example.edz.presenter;

import com.example.edz.contract.LoginContract;
import com.example.edz.listener.HttpResponseListener;
import com.example.edz.model.LoginMdl;
import com.mvp.BasePresenter;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginUi> implements LoginContract.LoginPtr {
    private LoginContract.LoginMdl loginMdl;

    public LoginPresenter(LoginContract.LoginUi view) {
        super(view);
        loginMdl = new LoginMdl();
    }

    @Override
    public void login(String username, String password) {
        loginMdl.login(username, password, new HttpResponseListener() {

            @Override
            public void onSuccess(Object tag, String t) {
                if (isViewAttach()) {
                    getView().loginSuccess(t);
                }
            }

            @Override
            public void onFailure(Object tag, String failure) {
                if (isViewAttach()) {
                    getView().loginFaild(failure);
                }
            }
        });
    }
}
