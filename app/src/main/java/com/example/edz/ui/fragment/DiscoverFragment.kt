package com.example.edz.ui.fragment

import com.example.edz.application.R
import com.example.edz.application.databinding.FragmentDiscoverBinding
import com.example.edz.viewmodel.DiscoverViewModel
import com.mvvm.BaseMvvmFragment

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class DiscoverFragment : BaseMvvmFragment<DiscoverViewModel, FragmentDiscoverBinding>() {
    override fun attachLayoutRes(): Int = R.layout.fragment_discover

    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.titleTv.text = "this is discover tv"
    }

    override fun initListener() {
    }

    override fun doBusiness() {

    }
}