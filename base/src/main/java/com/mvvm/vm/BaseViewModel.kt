package com.mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * author : zhengweishuai
 * date : 2020/6/8 0008.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
open class BaseViewModel() : ViewModel(), IViewModelAction {
    var vmLiveData = MutableLiveData<ViewModelActionEvent>()
    override fun showLoading() {
        val event = ViewModelActionEvent(ViewModelConstant.ACTION_SHOW_LOADING)
        vmLiveData.postValue(event)
    }

    override fun hideLoading() {
        val event = ViewModelActionEvent(ViewModelConstant.ACTION_HIDE_LOADING)
        vmLiveData.postValue(event)
    }

    override fun showToast() {
        val event = ViewModelActionEvent(ViewModelConstant.ACTION_SHOW_TOAST)
        vmLiveData.postValue(event)
    }

    override fun showErrorPopup() {
        val event = ViewModelActionEvent(ViewModelConstant.ACTION_SHOW_ERROR_POPUP)
        vmLiveData.postValue(event)
    }
}