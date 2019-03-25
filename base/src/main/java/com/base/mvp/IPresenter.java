package com.base.mvp;

import android.support.annotation.UiThread;

import java.io.Serializable;

/**
 * Created by zhengweis on 2017/11/15.
 */
public interface IPresenter<V extends IView> extends Serializable{

    @UiThread
    void attachView(V view);

    @UiThread
    void detachView();
}
