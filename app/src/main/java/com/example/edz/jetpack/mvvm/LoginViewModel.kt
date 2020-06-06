package com.example.edz.jetpack.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var name = MutableLiveData<String>()
    var pwd = MutableLiveData<String>()

    fun test(){
        Transformations.map(name, {it})
    }
}