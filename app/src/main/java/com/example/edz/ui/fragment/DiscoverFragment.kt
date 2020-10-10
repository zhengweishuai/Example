package com.example.edz.ui.fragment

import com.example.edz.R
import com.example.edz.databinding.FragmentDiscoverBinding
import com.example.edz.ui.adapter.DiscoverPageAdapter
import com.example.edz.viewmodel.DiscoverViewModel
import com.mvvm.BaseMvvmFragment
import kotlinx.android.synthetic.main.fragment_discover.*

/**
 * author : zhengweishuai
 * date : 2020/6/3 0003.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：发现
 */
class DiscoverFragment : BaseMvvmFragment<DiscoverViewModel, FragmentDiscoverBinding>() {
    private val tabs = arrayListOf("公众号", "体系", "项目")

    override fun attachLayoutRes(): Int = R.layout.fragment_discover

    override fun initViews() {
        mDataBind.vm = mViewModel
        mDataBind.adapter = DiscoverPageAdapter(parentFragmentManager, tabs)
        tb_layout.setupWithViewPager(vp, false)
        for (i in tabs.indices) {
            tb_layout.addTab(tb_layout.newTab())
            tb_layout.getTabAt(i)?.text = tabs[i]
        }
        vp.offscreenPageLimit = 3
    }

    override fun initListener() {
    }

    override fun doBusiness() {

    }
}