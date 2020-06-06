package com.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utils.getClazz

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：mvvm activity的基类
 */
abstract class BaseMvvmActivity<vm : ViewModel, db : ViewDataBinding> : AppCompatActivity() {
    lateinit var mDataBind: db
    lateinit var mViewModel: vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBind = DataBindingUtil.setContentView(this, attachLayoutRes())
        mViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(getClazz(this))
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