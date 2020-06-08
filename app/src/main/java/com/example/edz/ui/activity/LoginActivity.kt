package com.example.edz.ui.activity

import com.example.edz.application.R
import com.example.edz.application.databinding.ActivityLoginBinding
import com.example.edz.viewmodel.LoginViewModel
import com.mvvm.BaseMvvmActivity

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class LoginActivity : BaseMvvmActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun attachLayoutRes(): Int = R.layout.activity_login


    override fun initViews() {
        mDataBind.vm = mViewModel
    }

    override fun doListener() {
        mDataBind.btnLogin.setOnClickListener {

        }
    }

    override fun doBusiness() {
    }

    fun ProxyClick(){
        fun toRegister(){
        }
    }
}