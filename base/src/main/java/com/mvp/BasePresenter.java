package com.mvp;

import java.lang.ref.WeakReference;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter {
    // 防止 Activity 不走 onDestory() 方法，所以采用弱引用来防止内存泄漏
    private WeakReference<V> mViewRef;

    public BasePresenter(V view) {
        attachView(view);
    }

    public V getView() {
        return mViewRef.get();
    }

    private void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    @Override
    public boolean isViewAttach() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
