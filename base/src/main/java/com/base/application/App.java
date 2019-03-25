package com.base.application;

import android.app.Activity;
import android.app.Application;

import com.base.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by zhengweis on 2018/12/21.
 * description：
 */
public class App extends Application {
    private List<Activity> oList;//用于存放所有启动的Activity的集合

    @Override
    public void onCreate() {
        super.onCreate();
        //activtys
        oList = new ArrayList<>();
        //SharedPreferences
        SPUtils.init(this);
    }

    /**
     * 添加Activity
     */
    public void addActivity(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!oList.contains(activity)) {
            oList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : oList) {
            activity.finish();
        }
    }
}
