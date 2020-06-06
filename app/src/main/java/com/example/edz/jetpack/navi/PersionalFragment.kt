package com.example.edz.jetpack.navi

import android.content.Intent
import com.example.edz.application.R
import com.example.edz.application.databinding.FragmentPersionalBinding
import com.example.edz.jetpack.mvvm.LoginActivity
import com.mvvm.BaseMvvmFragment

/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class PersionalFragment : BaseMvvmFragment<PersionalViewModel, FragmentPersionalBinding>() {
    override fun attachLayoutRes(): Int = R.layout.fragment_persional

    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.userName.text = "this is persional tv"
    }

    override fun initListener() {
    }

    override fun doBusiness() {
        startActivity(Intent(requireActivity(), LoginActivity::class.java))
    }
}