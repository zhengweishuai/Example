package com.example.edz.jetpack.navi

import androidx.lifecycle.Observer
import com.example.edz.application.R
import com.example.edz.application.databinding.FragmentHomeBinding
import com.mvvm.BaseMvvmFragment


/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class HomeFragment : BaseMvvmFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.titleTv.text = "this is home tv"
    }

    override fun initListener() {
        mViewModel.data.observe(this, Observer {

        })
    }

    override fun doBusiness() {

    }
}