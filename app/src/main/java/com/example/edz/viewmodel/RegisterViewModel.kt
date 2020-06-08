package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.response.RegisterResponse
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request
import com.utils.LogUtil

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class RegisterViewModel() : BaseViewModel() {

    var inetResponse = MutableLiveData<RegisterResponse>()
    fun register(name: String, pwd: String) {
        request({
            val map = mutableMapOf<String, String>()
            map["username"] = name
            map["password"] = pwd
            map["repassword"] = pwd
            NetworkHelper.newInstance().register(map)
        }, {
            inetResponse.postValue(it)
        }, {
            LogUtil.d(it.toString())
        })
    }
}