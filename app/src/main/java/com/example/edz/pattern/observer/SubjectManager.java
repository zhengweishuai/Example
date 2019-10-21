package com.example.edz.pattern.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * created by zhengweis on 2019/10/15.
 * description：
 */
public class SubjectManager implements ISubject {

    private static SubjectManager manager;
    private List<IObserver> observerList;

    private SubjectManager() {
        observerList = new ArrayList<>();
    }

    public static SubjectManager newInstance() {
        if (manager == null) {
            manager = new SubjectManager();
        }
        return manager;
    }

    @Override
    public void attach(IObserver observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    @Override
    public void detach(IObserver observer) {
        Iterator<IObserver> iterator = observerList.iterator();
        while (iterator.hasNext()) {
            IObserver obj = iterator.next();
            if (obj == observer)
                iterator.remove();   //注意这个地方
        }
    }

    @Override
    public void postObserver(Object object) {
        for (IObserver observer : observerList) {
            observer.onEvent(object);
        }
    }
}
