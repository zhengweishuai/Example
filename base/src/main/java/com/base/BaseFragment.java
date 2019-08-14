package com.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.IBasePresenter;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.widget.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends RxFragment{

    //view
    private View mRootView;
    private boolean mIsMulti = false;
    private P presenter;
    /**
     * 回调监听接口
     */
    protected FragmentListener callback;
    protected Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), container, false);
            bind = ButterKnife.bind(this, mRootView);
            initViews();
            doBusiness();
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * 绑定布局文件
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 处理业务
     */
    protected abstract void doBusiness();

    /**
     * 懒加载更新视图控件
     */
    protected abstract void updateViews();

    /**
     * 创建 Presenter
     */
    public abstract P onBindPresenter();


    /**
     * 获取 Presenter 对象，在需要获取时才创建Presenter，起到懒加载作用
     */
    public P getPresenter() {
        if (presenter == null) {
            presenter = onBindPresenter();
        }
        return presenter;
    }

    public void toast(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    /**
     * 设置回调
     */
    public void setCallback(FragmentListener callback) {
        this.callback = callback;
    }
}
