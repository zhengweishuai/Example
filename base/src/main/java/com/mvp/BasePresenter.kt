package com.mvp

import java.lang.ref.WeakReference

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
open class BasePresenter<V : IBaseView?>(view: V) : IBasePresenter {
    // 防止 Activity 不走 onDestory() 方法，所以采用弱引用来防止内存泄漏
    private var mViewRef: WeakReference<V?>? = null
    val view: V?
        get() = mViewRef!!.get()

    private fun attachView(view: V) {
        mViewRef = WeakReference(view)
    }

    override fun isViewAttach(): Boolean {
        return mViewRef?.get() != null
    }

    override fun detachView() {
        if (mViewRef != null) {
            mViewRef?.clear()
            mViewRef = null
        }
    }

    init {
        attachView(view)
    }
}