package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request

/**
 * author : zhengweishuai
 * date : 2020/6/18 0018.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class WebViewModel : BaseViewModel() {
    var isollect = MutableLiveData<Boolean>()

    fun collectArticle(id: Int) {
        request({
            NetworkHelper.newInstance().collectArticle(id)
        }, {
            showToast("收藏成功")
            isollect.postValue(true)
        }, showLoading = true, showToast = true)
    }

    fun unCollectArticle(id: Int, orginId: Int) {
        request({
            NetworkHelper.newInstance().cancelCollectArticle(id, orginId)
        }, {
            showToast("取消收藏")
            isollect.postValue(false)
        }, showLoading = true, showToast = true)
    }
}