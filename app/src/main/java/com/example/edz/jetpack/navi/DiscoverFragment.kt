package com.example.edz.jetpack.navi

import com.example.edz.application.R
import com.example.edz.application.databinding.FragmentDiscoverBinding
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