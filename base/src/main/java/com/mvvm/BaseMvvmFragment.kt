package com.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.ViewModelConstant
import com.utils.LogUtil
import com.utils.getClazz

/**
 * author : zhengweishuai
 * date : 2020/6/4 0004.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
abstract class BaseMvvmFragment<vm : BaseViewModel, db : ViewDataBinding> : Fragment() {
    private var isLoad = false;//
    lateinit var mDataBind: db
    lateinit var mViewModel: vm
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(attachLayoutRes(), container, false)
        mDataBind = DataBindingUtil.bind<db>(rootView)!!.also {
            //感知生命周期
            it.lifecycleOwner = this
        }
        mViewModel = ViewModelProvider(this).get(getClazz(this))
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListener()
        doBusiness()
        doViewModelBusiness()
    }

    override fun onResume() {
        super.onResume()
        if (!isLoad) lazyLoad()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoad = false
    }

    private fun doViewModelBusiness() {
        mViewModel.vmLiveData.observe(viewLifecycleOwner, Observer {
            var activity: BaseMvvmActivity<*,*> = requireActivity() as BaseMvvmActivity<*, *>
            when (it.action) {
                ViewModelConstant.ACTION_SHOW_LOADING -> {
                    LogUtil.d("显示loading")
                    activity.showLoading(true)
                }
                ViewModelConstant.ACTION_HIDE_LOADING -> {
                    LogUtil.d("隐藏loading")
                    activity.showLoading(false)
                }
                ViewModelConstant.ACTION_SHOW_TOAST -> {
                    activity.showToast(it.msg)
                }
                ViewModelConstant.ACTION_SHOW_ERROR_POPUP -> {

                }
            }
        })
    }

    /*
    绑定布局
    */
    @LayoutRes
    abstract fun attachLayoutRes(): Int

    /*
    初始化view
     */
    abstract fun initViews()

    /*
    初始化监听
     */
    abstract fun initListener()

    /*
    处理业务逻辑
     */
    abstract fun doBusiness()

    /*
    懒加载
     */
    protected open fun lazyLoad() {
        isLoad = true
    }
}