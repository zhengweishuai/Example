package com.example.edz.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.constant.AppStaticRes
import com.example.edz.R
import com.example.edz.databinding.FragmentHomeBinding
import com.example.edz.ui.activity.ActivitySearch
import com.example.edz.ui.activity.LoginActivity
import com.example.edz.ui.activity.WebViewActivity
import com.example.edz.ui.adapter.HomeAdapter
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

    //点击的文章position
    private var clickPosition = -1

    //适配器
    val homeAdapter by lazy {
        HomeAdapter(requireContext())
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initViews() {
        mDataBind.vm = mViewModel
        iv_left.setBackgroundResource(R.drawable.filter_icon)
        iv_right.setBackgroundResource(R.drawable.search_icon)
        middle_title.text = "玩安卓"
        //recyclerview
        mDataBind.manager = LinearLayoutManager(requireActivity())
        mDataBind.rvAdapter = homeAdapter

        //文章列表
        mViewModel.articleData.observe(this@HomeFragment, Observer { response ->
            if (refresh_view.isRefreshing) refresh_view.finishRefresh()
            if (refresh_view.isLoading) refresh_view.finishLoadMore()
            response.datas?.let {
                if (pageNum == 0) {
                    homeAdapter.articles.clear()
                }
                homeAdapter.updateArticles(it)
            }
            if (homeAdapter.articles.size >= response.total) {
                refresh_view.finishLoadMoreWithNoMoreData()
            }
        })
        //banner
        mViewModel.bannerData.observe(this@HomeFragment, Observer {
            homeAdapter.updateBanners(it)
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
        homeAdapter.setOnItemClickListener(object : HomeAdapter.onItemClickListener {
            override fun onBannerClickListener(position: Int) {
                if (homeAdapter.banners.size > position){
                    val bannerBean = homeAdapter.banners[clickPosition]
                    WebViewActivity.start(this@HomeFragment,
                            bannerBean.url,
                            bannerBean.id.toInt(),
                            0,
                            false)
                }
            }

            override fun onArticleClickListener(position: Int) {
                clickPosition = position
                val articleListItemBean = homeAdapter.articles[clickPosition]
                WebViewActivity.start(this@HomeFragment,
                        articleListItemBean.link,
                        articleListItemBean.id,
                        articleListItemBean.originId,
                        articleListItemBean.collect)
            }
        })
    }

    override fun doBusiness() {
        mViewModel.requestBanners()
        refresh_view.autoRefresh()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppStaticRes.ARTICLE_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            data?.let { intent ->
                mDataBind.rvAdapter?.let {
                    val isCollect = intent.getBooleanExtra("is_collect", false)
                    homeAdapter.articles[clickPosition].collect = isCollect
                    it.notifyItemChanged(clickPosition)
                }
            }
        }
    }
}