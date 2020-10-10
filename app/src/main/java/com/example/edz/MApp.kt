package com.example.edz

import android.app.Activity
import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.utils.SPUtils
import com.widget.toast.ToastUtils
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.onAdaptListener
import me.jessyan.autosize.utils.ScreenUtils

/**
 * created by zhengweis on 2018/12/21.
 * description：
 */
class MApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //SharedPreferences
        SPUtils.init(this)
        //toast
        ToastUtils.init(this)
        //log
        LogUtils.getConfig().globalTag = "WAZ-->"
        //屏幕适配
        initAutoSize()
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }

    private fun initAutoSize() {
        AutoSizeConfig.getInstance() //是否屏蔽系统字体大小对 AndroidAutoSize 的影响, 如果为 true, App 内的字体的大小将不会跟随系统设置中字体大小的改变
                //如果为 false, 则会跟随系统设置中字体大小的改变, 默认为 false
                .setExcludeFontScale(true) //屏幕适配监听器
                .setOnAdaptListener(object : onAdaptListener {
                    override fun onAdaptBefore(target: Any, activity: Activity) {
                        //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                        //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
                        AutoSizeConfig.getInstance().screenWidth = ScreenUtils.getScreenSize(activity)[0]
                        AutoSizeConfig.getInstance().screenHeight = ScreenUtils.getScreenSize(activity)[1]
                    }

                    override fun onAdaptAfter(target: Any, activity: Activity) {}
                }).isCustomFragment = true
    }

    companion object {
        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ -> MaterialHeader(context) }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> ClassicsFooter(context) }
        }
    }
}