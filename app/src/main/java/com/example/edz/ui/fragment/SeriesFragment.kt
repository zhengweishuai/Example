package com.example.edz.ui.fragment

import com.example.edz.R
import com.example.edz.databinding.FragmentSeriesBinding
import com.example.edz.databinding.FragmentWxArticleBinding
import com.example.edz.viewmodel.SeriesViewModel
import com.example.edz.viewmodel.WxArticleViewModel
import com.mvvm.BaseMvvmFragment

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class SeriesFragment :BaseMvvmFragment<SeriesViewModel, FragmentSeriesBinding>(){
    override fun attachLayoutRes(): Int  = R.layout.fragment_series

    override fun initViews() {
    }

    override fun initListener() {
    }

    override fun doBusiness() {
    }
}