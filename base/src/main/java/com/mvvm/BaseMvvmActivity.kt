package com.mvvm

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.base.R
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.ViewModelConstant
import com.utils.LogUtil
import com.utils.getClazz
import com.widget.toast.ToastUtils
import kotlinx.android.synthetic.main.layout_base_loading.*

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
        mDataBind.root.setBackgroundColor(ContextCompat.getColor(this, R.color.color_f2f4f5))
        initViews()
        doListener()
        doBusiness()
        doImmersionbasr()
        doViewModelBusiness()
    }

    private fun doImmersionbasr() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.white)
            navigationBarColor(R.color.color_f2f4f5)
            statusBarDarkFont(true)
        }
    }

    private fun doViewModelBusiness() {
        mViewModel.vmLiveData.observe(this, Observer {
            when (it.action) {
                ViewModelConstant.ACTION_SHOW_LOADING -> {
                    LogUtil.d("显示loading")
                    showLoading(true)
                }
                ViewModelConstant.ACTION_HIDE_LOADING -> {
                    LogUtil.d("隐藏loading")
                    showLoading(false)
                }
                ViewModelConstant.ACTION_SHOW_TOAST -> {
                    showToast(it.msg)
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

    override fun onDestroy() {
        showLoading(false)
        super.onDestroy()
    }

    fun showToast(msg: String) {
        ToastUtils.show(msg)
    }

    private var loadCount = 0
    private var loadingLayout: View? = null
    fun showLoading(show: Boolean) {
        if (loadingLayout == null) {
            loadingLayout = LayoutInflater.from(this).inflate(R.layout.layout_base_loading, null)
        }
        val viewGroup = mDataBind.root as? ViewGroup
        if (show) {
            if (loadCount == 0) {
                LogUtil.d("显示showLoading")
                viewGroup?.addView(loadingLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                loadCount++
                startRotate(loading)
            }
        } else {
            if (loadCount > 0) {
                LogUtil.d("隐藏showLoading")
                viewGroup?.removeView(loadingLayout)
            }
            loadCount = 0
        }
    }

    /**
     * 旋转动画
     */
    @SuppressLint("WrongConstant")
    private fun startRotate(view: View) {
        val rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        rotateAnimator.duration = 1000
        rotateAnimator.repeatMode = ValueAnimator.INFINITE
        rotateAnimator.repeatCount = ValueAnimator.INFINITE
        rotateAnimator.start()
    }
}