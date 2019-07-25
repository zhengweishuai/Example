package com.mvp;

/**
 * created by zhengweis on 2019/7/25.
 * description：
 */
public interface IBasePresenter {
    /**
     * 判断 presenter 是否与 view 建立联系，防止出现内存泄露状况
     *
     * @return true: 联系已建立、false: 联系已断开
     */
    boolean isViewAttach();

    /**
     * 断开 presenter 与 view 直接的联系
     */
    void detachView();
}
