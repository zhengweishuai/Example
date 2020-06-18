package com.example.edz.viewmodel

import com.example.edz.api.NetworkHelper
import com.constant.NetUrls
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request

/**
 * author : zhengweishuai
 * date : 2020/6/18 0018.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class WebViewModel : BaseViewModel() {
    fun collectArticle(id: Int?) {
        request({
            val url = NetUrls.COLLECT_ARTICLE + id + "/json"
            NetworkHelper.newInstance().collectArticle(url)
        }, {
            showToast("收藏成功")
        }, showLoading = true, showToast = true)
    }
}