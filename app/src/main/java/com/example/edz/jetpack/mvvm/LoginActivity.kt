package com.example.edz.jetpack.mvvm

import com.example.edz.api.NetworkHelp
import com.example.edz.application.R
import com.example.edz.application.databinding.ActivityLoginBinding
import com.example.edz.bean.request.LoginRequest
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
            val api = NetworkHelp.newInstance()
            api.website()
//            api.login(LoginRequest("111", "111"))
        }
    }

    override fun doBusiness() {

    }
}