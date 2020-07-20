package com.example.edz.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.constant.AppStaticRes
import com.example.edz.BR
import com.example.edz.R
import com.example.edz.databinding.FragmentHomeBinding
import com.example.edz.ui.activity.ActivitySearch
import com.example.edz.ui.activity.LoginActivity
import com.example.edz.ui.activity.WebViewActivity
import com.example.edz.ui.adapter.HomeArticleAdapter
import com.example.edz.ui.widget.PersionalPopup
import com.example.edz.utils.UserDataUtil
import com.example.edz.viewmodel.HomeViewModel
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupPosition
import com.mvvm.BaseMvvmFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_base_title.*


/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：首页
 */
class HomeFragment : BaseMvvmFragment<HomeViewModel, FragmentHomeBinding>() {
    private var pageNum: Int = 0 //接口请求页码
    private var clickPosition = -1 //点击的position

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initViews() {
        mDataBind.vm = mViewModel
        iv_left.setBackgroundResource(R.drawable.filter_icon)
        iv_right.setBackgroundResource(R.drawable.search_icon)
        middle_title.text = "玩安卓"
        //recyclerview
        mDataBind.manager = LinearLayoutManager(requireActivity())
        mDataBind.rvAdapter = HomeArticleAdapter(requireActivity(), br_id = BR.itemBeam, onItemClick = {
            clickPosition = it
            val articleListItemBean = mDataBind.rvAdapter!!.mList[clickPosition]
            WebViewActivity.start(this,
                    articleListItemBean.link,
                    articleListItemBean.id,
                    articleListItemBean.originId,
                    articleListItemBean.collect)
        }).apply {
            mViewModel.articleData.observe(this@HomeFragment, Observer { response ->
                if (refresh_view.isRefreshing) refresh_view.finishRefresh()
                if (refresh_view.isLoading) refresh_view.finishLoadMore()
                response.datas?.let {
                    if (pageNum == 0) setNewData(it) else addList(it)
                }
                if (this.mList.size >= response.total) {
                    refresh_view.finishLoadMoreWithNoMoreData()
                }
            })
        }
        //banner
        mViewModel.bannerData.observe(this@HomeFragment, Observer {
            cus_banner.setData(it)
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        refresh_view.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshLayout.resetNoMoreData()
                pageNum = 0
                mViewModel.requestArticleList(pageNum)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageNum++
                mViewModel.requestArticleList(pageNum)
            }
        })
        rl_left.setOnClickListener {
            if (UserDataUtil.getUserData() == null) {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
            } else {
                XPopup.Builder(requireActivity())
                        .popupPosition(PopupPosition.Left)
                        .hasStatusBarShadow(false)
                        .asCustom(PersionalPopup(requireActivity()))
                        .show()
            }
        }
        rl_right.setOnClickListener {
            ActivitySearch.start(requireActivity())
        }
    }

    override fun doBusiness() {
        mViewModel.requestBanners()
        refresh_view.autoRefresh()
    }

    override fun onStop() {
        super.onStop()
        cus_banner.onStop()
    }

    override fun onResume() {
        super.onResume()
        cus_banner.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppStaticRes.ARTICLE_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            data?.let { intent ->
                mDataBind.rvAdapter?.let {
                    val isCollect = intent.getBooleanExtra("is_collect", false)
                    it.mList[clickPosition].collect = isCollect
                    it.notifyItemChanged(clickPosition)
                }
            }
        }
    }
}