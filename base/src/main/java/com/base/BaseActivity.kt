package com.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * author : zhengweishuai
 * date : 2020/6/5 0005.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：基类activity
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRootView = LayoutInflater.from(this).inflate(attachLayoutRes(), null)
        setContentView(mRootView)
        initViews()
        doListener()
        doBusiness()
    }

    /**
     * 绑定布局
     */
    abstract fun attachLayoutRes(): Int

    /**
     * 初始化视图控件
     */
    abstract fun initViews()

    /**
     * 处理监听
     */
    abstract fun doListener()

    /**
     * 处理业务
     */
    abstract fun doBusiness()
}