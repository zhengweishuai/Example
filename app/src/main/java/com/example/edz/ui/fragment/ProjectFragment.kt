package com.example.edz.ui.fragment

import com.example.edz.application.R
import com.example.edz.application.databinding.FragmentProjectBinding
import com.example.edz.viewmodel.ProjectViewModel
import com.mvvm.BaseMvvmFragment

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectFragment :BaseMvvmFragment<ProjectViewModel, FragmentProjectBinding>(){
    override fun attachLayoutRes(): Int  = R.layout.fragment_project

    override fun initViews() {
    }

    override fun initListener() {
    }

    override fun doBusiness() {
    }
}