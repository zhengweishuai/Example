package com.example.edz.ui.activity

import android.content.Intent
import android.text.TextUtils
import androidx.lifecycle.Observer
import com.example.edz.application.R
import com.example.edz.application.databinding.ActivityRegisterBinding
import com.example.edz.viewmodel.RegisterViewModel
import com.mvvm.BaseMvvmActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.layout_base_title.*

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：注册
 */
class RegisterActivity : BaseMvvmActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override fun attachLayoutRes(): Int = R.layout.activity_register


    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.click = ProxyClick()

        //
        middle_title.text = "注册"
    }

    override fun doListener() {

    }

    override fun doBusiness() {
        mViewModel.inetResponse.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
        })
    }

    inner class ProxyClick() {
        fun toRegister() {
            if (!TextUtils.isEmpty(ed_name.text.toString())
                    && !TextUtils.isEmpty(ed_pwd.text.toString())
                    && TextUtils.equals(ed_pwd.text.toString(), ed_pwd_again.text.toString())) {
                mViewModel.register(ed_name.text.toString()
                        , ed_pwd.text.toString())
            } else {
                showToast("填写信息有误")
            }
        }
    }
}