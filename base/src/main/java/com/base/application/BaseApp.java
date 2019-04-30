package com.base.application;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.base.utils.DensityUtil;
import com.base.utils.SPUtils;
import com.base.widget.RongShuFloatWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * created by zhengweis on 2018/12/21.
 * description：
 */
public class BaseApp extends Application {
    private List<Activity> oList;//用于存放所有启动的Activity的集合
    protected Activity curTopActivity;
    private RongShuFloatWindow floatWindow;

    @Override
    public void onCreate() {
        super.onCreate();
        //activtys
        oList = new ArrayList<>();
        //SharedPreferences
        SPUtils.init(this);
        registerActivityLifeCallback();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d("MyApplication", "APP进入后台");
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Toast.makeText(this, "--------------", Toast.LENGTH_SHORT).show();
        }
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

    /**
     * 销毁Activity
     */
    public void removeActivity(Activity activity) {
        //通过循环，把集合中的所有Activity销毁
        if (activity != null) {
            activity.finish();
        }
    }

    protected void registerActivityLifeCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.i("MyApplication", "onActivityStarted");
                showFloatWindow();
            }

            @Override
            public void onActivityResumed(Activity activity) {
                curTopActivity = activity;
                Log.i("MyApplication", "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.i("MyApplication", "onActivityPaused");
                hideFloatWindow();
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.i("MyApplication", "onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public void showFloatWindow() {
        if (floatWindow == null) {
            floatWindow = new RongShuFloatWindow(getApplicationContext());
            Button button = new Button(this);
            button.setText("Floating Window");
            button.setBackgroundColor(Color.BLUE);
            floatWindow.setView(button);
            floatWindow.setGravity(Gravity.BOTTOM | Gravity.RIGHT,
                    0, DensityUtil.getScreenHeight(this) / 5);
            floatWindow.setMoreRange(DensityUtil.getScreenHeight(this) - 200, DensityUtil.getScreenHeight(this) / 5);
            floatWindow.show();
        } else {
            floatWindow.show();
        }
    }

    public void hideFloatWindow() {
        Toast.makeText(this, "--------------", Toast.LENGTH_SHORT).show();
        if (floatWindow != null) {
            floatWindow.dimiss();
        }
    }
}
