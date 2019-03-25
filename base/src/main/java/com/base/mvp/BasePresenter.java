package com.base.mvp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

/**
 * Created by zhengweis on 2017/11/15.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    private V viewRef;

    /**
     * The M context.
     */
    protected Context mContext;

    public BasePresenter(Context context) {
        mContext = context;
    }

    @UiThread
    @Override
    public void attachView(V view) {
        viewRef = view;
    }

    /**
     * Gets view.
     *
     * @return the view
     */
    @UiThread
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef;
    }

    /**
     * Is view attached boolean.
     *
     * @return the boolean
     */
    @UiThread
    public boolean isViewAttached() {
        return viewRef != null;
    }

    @UiThread
    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef = null;
        }
    }

}
