package com.example.edz.contract;

import com.mvp.IBasePresenter;
import com.mvp.IBaseView;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public class LoginContract {
    public interface LoginUi extends IBaseView {
        /**
         * 登录成功
         */
        void loginSuccess(String msg);

        /**
         * 登录失败
         */
        void loginFaild(String msg);
    }

    /**
     * presenter 层接口
     */
    public interface LoginPtr extends IBasePresenter {
        void login(String username, String password);
    }
}
