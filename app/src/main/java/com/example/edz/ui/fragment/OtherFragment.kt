package com.example.edz.ui.fragment

import com.example.edz.R
import com.example.edz.databinding.FragmentPersionalBinding
import com.example.edz.viewmodel.PersionalViewModel
import com.mvvm.BaseMvvmFragment

/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class OtherFragment : BaseMvvmFragment<PersionalViewModel, FragmentPersionalBinding>() {
    override fun attachLayoutRes(): Int = R.layout.fragment_persional

    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.userName.text = "this is persional tv"
    }

    override fun initListener() {
    }

    override fun doBusiness() {

    }
}