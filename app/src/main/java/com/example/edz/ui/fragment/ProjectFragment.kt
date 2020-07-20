package com.example.edz.ui.fragment

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.constant.AppStaticRes
import com.example.edz.BR
import com.example.edz.R
import com.example.edz.bean.ProjectCategoryBean
import com.example.edz.bean.ProjectDetailBean
import com.example.edz.databinding.FragmentProjectBinding
import com.example.edz.ui.activity.WebViewActivity
import com.example.edz.ui.adapter.ProjectAdapter
import com.example.edz.ui.widget.ScreenProjectCategoryPopup
import com.example.edz.viewmodel.ProjectViewModel
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.mvvm.BaseMvvmFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectFragment : BaseMvvmFragment<ProjectViewModel, FragmentProjectBinding>() {
    private var pageNum = 1
    private var mProjectId = 1
    private var categoryList: ArrayList<ProjectCategoryBean>? = null
    private var popup: BasePopupView? = null
    private var clickPosition = -1
    override fun attachLayoutRes(): Int = R.layout.fragment_project

    override fun initViews() {
        mDataBind.manager = GridLayoutManager(requireActivity(), 2)
        mDataBind.adapter = ProjectAdapter(requireActivity(),
                BR.projectItem,
                itemClick = {
                    clickPosition = it
                    val articleListItemBean = mDataBind.adapter!!.mList[clickPosition]
                    WebViewActivity.start(this,
                            articleListItemBean.link,
                            articleListItemBean.id,
                            0,
                            articleListItemBean.collect)
                }).apply {
            mViewModel.projectCategory.observe(this@ProjectFragment, Observer { list ->
                categoryList = list
                list[0]?.let {
                    mProjectId = it.id
                    project_title.text = it.name
                    refresh_view.autoRefresh()
                }

            })
            mViewModel.projectList.observe(this@ProjectFragment, Observer { response ->
                response.datas?.let {
                    if (pageNum == 1) setNewData(it) else addList(it)
                }
                refresh_view.apply {
                    if (isRefreshing) finishRefresh()
                    if (isLoading) finishLoadMore()
                }
                if (this.mList.size >= response.total) {
                    refresh_view.finishLoadMoreWithNoMoreData()
                }
            })
        }
    }

    override fun initListener() {
        refresh_view.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageNum++
                mViewModel.requestProjectList(pageNum, mProjectId)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshLayout.resetNoMoreData()
                pageNum = 1
                mViewModel.requestProjectList(pageNum, mProjectId)
            }

        })

        project_title_layout.setOnClickListener { view ->
            categoryList?.let { it ->
                if (popup == null) {
                    popup = XPopup.Builder(requireActivity())
                            .atView(view)
                            .asCustom(ScreenProjectCategoryPopup(requireActivity(),
                                    it,
                                    categoryListener = {
                                        mProjectId = it.id
                                        project_title.text = it.name
                                        refresh_view.autoRefresh()
                                    }))
                }
                if (!popup!!.isShown) {
                    popup!!.show()
                }
            }
        }
    }

    override fun doBusiness() {
    }

    override fun lazyLoad() {
        super.lazyLoad()
        mViewModel.requestProjectCategory()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppStaticRes.ARTICLE_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            data?.let { intent ->
                mDataBind.adapter?.let {
                    val isCollect = intent.getBooleanExtra("is_collect", false)
                    it.mList[clickPosition].collect = isCollect
                    it.notifyItemChanged(clickPosition)
                }
            }
        }
    }
}