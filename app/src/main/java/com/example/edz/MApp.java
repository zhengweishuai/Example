package com.example.edz;

import android.app.Activity;
import android.app.Application;

import com.base.BuildConfig;
import com.utils.LogUtil;
import com.utils.SPUtils;
import com.widget.FloatWindow;
import com.widget.toast.ToastUtils;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;
import me.jessyan.autosize.utils.ScreenUtils;

/**
 * created by zhengweis on 2018/12/21.
 * description：
 */
public class MApp extends Application {
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
        LogUtil.init(BuildConfig.IS_DEBUG, getPackageName());
        //屏幕适配
        initAutoSize();
    }

    private void initAutoSize() {
        AutoSizeConfig.getInstance()
                //是否屏蔽系统字体大小对 AndroidAutoSize 的影响, 如果为 true, App 内的字体的大小将不会跟随系统设置中字体大小的改变
                //如果为 false, 则会跟随系统设置中字体大小的改变, 默认为 false
                .setExcludeFontScale(true)
                //屏幕适配监听器
                .setOnAdaptListener(new onAdaptListener() {
                    @Override
                    public void onAdaptBefore(Object target, Activity activity) {
                        //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                        //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
                        AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
                        AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
                    }

                    @Override
                    public void onAdaptAfter(Object target, Activity activity) {

                    }
                })
                //支持fragment自定义参数
                .setCustomFragment(true);
    }
}
