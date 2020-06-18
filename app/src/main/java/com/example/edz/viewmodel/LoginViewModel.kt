package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.constant.SPConfigs
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.UserBean
import com.example.edz.utils.UserDataUtil
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request
import com.utils.SPUtils

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class LoginViewModel() : BaseViewModel() {

    var loginResponse = MutableLiveData<UserBean>()

    fun login(name: String, pwd: String) {
        request({
            val map = mutableMapOf<String, String>()
            map["username"] = name
            map["password"] = pwd
            NetworkHelper.newInstance().login(map)
        }, {
            //保存用户信息到本地
            SPUtils.putPreferences(SPConfigs.USER_ACCOUNT, name)
            SPUtils.putPreferences(SPConfigs.USER_PASSWORD, pwd)
            it?.let {
                UserDataUtil.putUserData(it)
                loginResponse.postValue(it)
            }
        }, {
        }, showLoading = true, showToast = true)
    }
}