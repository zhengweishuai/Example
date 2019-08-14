package com.http;


import android.content.Context;

import com.base.BaseActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * created by zhengweis on 2019/8/13.
 * description：
 */
public abstract class ApiCallBack<T> implements Observer<BaseResponse<T>> {
    private boolean show = true;
    private Context context;

    public ApiCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e, RxException.exceptionHandler(e));
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        if (tBaseResponse.getErrCode() == 0) {
            onSuccess(tBaseResponse.getResponse());
        } else {
            onFailure(null, tBaseResponse.getErrMsg());
        }
    }


    @Override
    public void onComplete() {
        showLoading(false);
    }

    @Override
    public void onSubscribe(Disposable d) {
        showLoading(isShowLoading());
    }

    private void showLoading(boolean show) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).showLoading(show);
        }
    }

    public abstract void onSuccess(T response);


    public abstract void onFailure(Throwable e, String errorMsg);

    /**
     * 是否显示loading
     */
    public boolean isShowLoading() {
        return show;
    }
}
