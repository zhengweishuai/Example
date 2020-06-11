package com.example.edz.utils

import android.text.TextUtils
import com.example.edz.bean.UserBean
import com.example.edz.constant.SPConfigs
import com.google.gson.Gson
import com.utils.SPUtils

/**
 * author : zhengweishuai
 * date : 2020/6/9 0009.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：操作当前用户信息
 */
object UserDataUtil {
    /*
    设置用户信息
     */
    fun putUserData(userBean: UserBean) {
        val toJson = Gson().toJson(userBean)
        SPUtils.putPreferences(SPConfigs.USER_DATA, toJson)
    }

    /*
    获取用户信息
     */
    fun getUserData(): UserBean? {
        val json = SPUtils.getPreferences(SPConfigs.USER_DATA, null)
        return if (TextUtils.isEmpty(json)) null else Gson().fromJson(json, UserBean::class.java)
    }

    /*
    清除本地的用户信息
     */
    fun clearUserData(){
        SPUtils.removePreferences(SPConfigs.USER_DATA)
    }
}