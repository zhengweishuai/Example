package com.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.listener.BaseListener;
import com.base.mvp.IPresenter;
import com.base.mvp.IView;
import com.base.widget.toast.ToastUtils;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<Presenter extends IPresenter> extends RxFragment {

    //view
    private View mRootView;
    public boolean mIsMulti = false;
    protected Presenter presenter;
    /**
     * 回调监听接口
     */
    public BaseListener callback;
    protected Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            bind = ButterKnife.bind(this, mRootView);
            initViews();
            initData();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * @param <T>
     * @return
     */
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }


    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 更新视图控件
     *
     * @param isRefresh 新增参数，用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
     */
    protected abstract void updateViews(boolean isRefresh);

    public void toast(String msg) {
        ToastUtils.show(msg);
    }

    /**
     * 绑定presenter view
     *
     * @param presenter
     * @param view
     * @param <V>
     */
    public <V extends IView> void setPresenter(Presenter presenter, V view) {
        this.presenter = presenter;
        this.presenter.attachView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    public void setCallback(BaseListener callback) {
        this.callback = callback;
    }
}
