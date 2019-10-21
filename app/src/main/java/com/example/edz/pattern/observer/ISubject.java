package com.example.edz.pattern.observer;

/**
 * created by zhengweis on 2019/10/15.
 * description：
 */
public interface ISubject {
    /**
     * 订阅
     * @param observer
     */
    void attach(IObserver observer);

    /**
     * 取消订阅
     * @param observer
     */
    void detach(IObserver observer);

    /**
     * 发送通知
     * @param object
     */
    void postObserver(Object object);
}
