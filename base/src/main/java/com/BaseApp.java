package com;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import com.base.BuildConfig;
import com.http.ApiHelper;
import com.utils.DensityUtil;
import com.utils.LogUtil;
import com.utils.SPUtils;
import com.widget.FloatWindow;
import com.widget.toast.ToastUtils;

/**
 * created by zhengweis on 2018/12/21.
 * description：
 */
public class BaseApp extends Application {
    protected Activity curTopActivity;
    private FloatWindow floatWindow;
    protected int actCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        //SharedPreferences
        SPUtils.init(this);
        //toast
        ToastUtils.init(this);
        //log
        LogUtil.init(BuildConfig.IS_DEBUG);
        //网络请求
        ApiHelper.init();
        registerActivityLifeCallback();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d("MyApplication", "APP进入后台");
    }

    protected void registerActivityLifeCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                actCount++;
                if (floatWindow != null) {
                    showFloatWindow();
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                curTopActivity = activity;
                Log.i("MyApplication", "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                actCount--;
                if (actCount <= 0) {
                    hideFloatWindow();
                }
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
            floatWindow = new FloatWindow(getApplicationContext());
            floatWindow.setGravity(Gravity.BOTTOM | Gravity.RIGHT,
                    30, DensityUtil.getScreenHeight(this) / 9);
            floatWindow.setMoreRange(DensityUtil.getScreenHeight(this) - 200,
                    DensityUtil.getScreenHeight(this) / 14);
            floatWindow.setCloseListener(new FloatWindow.CloseMiniWindowListener() {
                @Override
                public void close() {
                    floatWindow.clear();
                    floatWindow = null;
                }
            });
            floatWindow.show();
        } else {
            floatWindow.show();
        }
    }

    public void hideFloatWindow() {
        if (floatWindow != null) {
            floatWindow.dimiss();
        }
    }
}
