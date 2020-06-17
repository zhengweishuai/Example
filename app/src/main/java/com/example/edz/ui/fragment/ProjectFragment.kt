package com.example.edz.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.edz.application.BR
import com.example.edz.application.R
import com.example.edz.application.databinding.FragmentProjectBinding
import com.example.edz.ui.adapter.ProjectAdapter
import com.example.edz.viewmodel.ProjectViewModel
import com.mvvm.BaseMvvmFragment
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectFragment : BaseMvvmFragment<ProjectViewModel, FragmentProjectBinding>() {
    override fun attachLayoutRes(): Int = R.layout.fragment_project

    override fun initViews() {
        mDataBind.manager = GridLayoutManager(requireActivity(), 2)
        mDataBind.adapter = ProjectAdapter(requireActivity(), BR.projectItem).apply {
            mViewModel.projectList.observe(this@ProjectFragment, Observer {
                addList(it)
            })
        }
        rl_project.isNestedScrollingEnabled = false
        rl_project.itemAnimator = null
    }

    override fun initListener() {
    }

    override fun doBusiness() {
        mViewModel.requestProjectList()
    }
}