package com.example.edz.ui.activity

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.example.edz.R
import com.example.edz.databinding.ActivityLoginBinding
import com.example.edz.viewmodel.LoginViewModel
import com.mvvm.BaseMvvmActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_base_title.*

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：登录
 */
class LoginActivity : BaseMvvmActivity<LoginViewModel, ActivityLoginBinding>(), TextWatcher {

    override fun attachLayoutRes(): Int = R.layout.activity_login


    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.click = ProxyClick()
        middle_title.text = "登录"
        rl_left.setOnClickListener { finish() }
    }

    override fun doListener() {
        ed_name.addTextChangedListener(this)
        ed_pwd.addTextChangedListener(this)
    }

    override fun doBusiness() {
        intent.getStringExtra("nikeName")?.let {
            ed_name.setText(it)
        }
        mViewModel.loginResponse.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            setResult(Activity.RESULT_OK)
            finish()
        })
    }

    inner class ProxyClick() {
        fun login() {
            if (!TextUtils.isEmpty(ed_name.text.toString()) && !TextUtils.isEmpty(ed_pwd.text.toString())) {
                mViewModel.login(ed_name.text.toString(), ed_pwd.text.toString())
            } else {
                showToast("请检查输出信息是否有误")
            }
        }

        fun toRegister() {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    override fun afterTextChanged(s: Editable?) {
        btn_login.setBackgroundResource(
                if (!TextUtils.isEmpty(ed_name.text.toString()) && !TextUtils.isEmpty(ed_pwd.text.toString()))
                    R.drawable.base_shape_btn_selected else
                    R.drawable.base_shape_btn_default)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}