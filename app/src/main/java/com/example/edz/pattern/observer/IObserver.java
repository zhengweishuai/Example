package com.example.edz.pattern.observer;

/**
 * created by zhengweis on 2019/10/15.
 * description：
 */
public interface IObserver {
    /**
     * 接受事件
     *
     * @param object
     */
    void onEvent(Object object);
}
