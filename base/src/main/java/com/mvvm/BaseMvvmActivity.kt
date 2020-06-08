package com.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.ViewModelConstant
import com.utils.getClazz
import com.widget.toast.ToastUtils

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：mvvm activity的基类
 */
abstract class BaseMvvmActivity<vm : BaseViewModel, db : ViewDataBinding> : AppCompatActivity() {
    lateinit var mDataBind: db
    lateinit var mViewModel: vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBind = DataBindingUtil.setContentView(this, attachLayoutRes())
        mViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(getClazz(this))
        mDataBind.lifecycleOwner = this
        initViews()
        doListener()
        doBusiness()
        doViewModelBusiness()
    }

    private fun doViewModelBusiness() {
        mViewModel.vmLiveData.observe(this, Observer {
            when (it.action) {
                ViewModelConstant.ACTION_SHOW_LOADING -> {
                    showToast("显示Loading")
                }
                ViewModelConstant.ACTION_HIDE_LOADING -> {
                    showToast("隐藏Loading")
                }
                ViewModelConstant.ACTION_SHOW_TOAST -> {

                }
                ViewModelConstant.ACTION_SHOW_ERROR_POPUP -> {

                }
            }
        })
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

    fun showToast(msg: String) {
        ToastUtils.show(msg)
    }
}