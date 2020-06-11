package com.mvvm.vm

/**
 * author : zhengweishuai
 * date : 2020/6/8 0008.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：viewModel事件
 */
interface IViewModelAction {
    /*
    显示loading
     */
    fun showLoading()

    /*
    隐藏loading
     */
    fun hideLoading()

    /*
    显示toast
     */
    fun showToast(msg: String)

    /*
    显示异常弹窗
     */
    fun showErrorPopup()
}