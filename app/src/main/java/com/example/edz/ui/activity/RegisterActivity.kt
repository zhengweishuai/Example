package com.example.edz.ui.activity

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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
class RegisterActivity : BaseMvvmActivity<RegisterViewModel, ActivityRegisterBinding>(), TextWatcher {

    override fun attachLayoutRes(): Int = R.layout.activity_register


    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.click = ProxyClick()

        //
        middle_title.text = "注册"
        rl_left.setOnClickListener { finish() }
    }

    override fun doListener() {
        ed_name.addTextChangedListener(this)
        ed_pwd.addTextChangedListener(this)
        ed_pwd_again.addTextChangedListener(this)
    }

    override fun doBusiness() {
        mViewModel.inetResponse.observe(this, Observer {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("nikeName", it.nickname)
            startActivity(intent)
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


    override fun afterTextChanged(s: Editable?) {
        btn_register.setBackgroundResource(
                if (!TextUtils.isEmpty(ed_name.text.toString()) &&
                        !TextUtils.isEmpty(ed_pwd.text.toString()) &&
                        !TextUtils.isEmpty(ed_pwd_again.text.toString()))
                    R.drawable.base_shape_btn_selected else
                    R.drawable.base_shape_btn_default)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}