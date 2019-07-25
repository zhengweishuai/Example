package com.mvp;

import android.app.Activity;

import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public interface IBaseView {
    /**
     * 获取activity
     */
    <T extends Activity> T getSelfActivity();

    /**
     * 绑定生命周期
     * rxjava+retrofit进行网络请求，使用该方法与act绑定生命周期
     * 不使用rxjava+retrofit，可以废弃
     */
    <T> LifecycleTransformer<T> bindToLife();
}
