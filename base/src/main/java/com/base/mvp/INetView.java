package com.base.mvp;

/**
 * Created by zhengweis on 2017/11/15.
 */
public interface INetView extends IView {
    void success(Object object, int tag);

    void faild(Object object, int tag);
}
