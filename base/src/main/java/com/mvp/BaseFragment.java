package com.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.widget.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment {

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
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        doBusiness();
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
     * @param mRootView
     */
    protected abstract void initViews(View mRootView);

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
