package com.example.edz.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.constant.AppStaticRes
import com.example.edz.BR
import com.example.edz.R
import com.example.edz.bean.HomeArticleListResponse
import com.example.edz.bean.SeriesCollectBean
import com.example.edz.databinding.FragmentArticleSeriesListBinding
import com.example.edz.ui.activity.WebViewActivity
import com.example.edz.ui.adapter.HomeArticleAdapter
import com.example.edz.viewmodel.ArticleSeriesListViewModel
import com.mvvm.BaseMvvmFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_article_series_list.*

/**
 * author : zhengweishuai
 * date : 2020/7/1 0001.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：体系下文章列表
 */
class ArticleSeriesListFragment : BaseMvvmFragment<ArticleSeriesListViewModel, FragmentArticleSeriesListBinding>() {
    private var clickPosition = -1 //点击的position

    //接口返回数据
    private var mArticleResponse: HomeArticleListResponse? = null

    //请求页码
    private var pageNum: Int = 0

    //上个页面传递参数
    private val mSeriesBean by lazy {
        arguments?.getSerializable("SeriesCollectBean") as SeriesCollectBean
    }

    companion object {
        fun newInstance(series: SeriesCollectBean): ArticleSeriesListFragment {
            val args = Bundle()
            args.putSerializable("SeriesCollectBean", series)
            val fragment = ArticleSeriesListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_article_series_list

    override fun initViews() {
        refresh_view.setEnableAutoLoadMore(false)
        mDataBind.vm = mViewModel
        mDataBind.manager = LinearLayoutManager(requireActivity())
        mDataBind.rvAdapter = HomeArticleAdapter(requireActivity(), br_id = BR.itemBeam, onItemClick = { position ->
            mArticleResponse?.datas?.let {
                clickPosition = position
                val articleListItemBean = mDataBind.rvAdapter!!.mList[position]
                WebViewActivity.start(this,
                        articleListItemBean.link,
                        articleListItemBean.id,
                        articleListItemBean.originId,
                        articleListItemBean.collect)
            }
        }).apply {
            mViewModel.articleData.observe(this@ArticleSeriesListFragment, Observer { response ->
                mArticleResponse = response
                response.datas?.let {
                    if (pageNum == 0) this.setNewData(it) else this.addList(it)
                }
                if (refresh_view.isRefreshing) refresh_view.finishRefresh()
                if (refresh_view.isLoading) refresh_view.finishLoadMore()
                if (this.mList.size >= response.total) refresh_view.finishLoadMoreWithNoMoreData()
            })
        }

    }

    override fun initListener() {
        refresh_view.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageNum++
                mViewModel.requestArticleList(pageNum, mSeriesBean.id)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageNum = 0
                refreshLayout.resetNoMoreData()
                mViewModel.requestArticleList(pageNum, mSeriesBean.id)
            }

        })
    }

    override fun doBusiness() {
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

    override fun lazyLoad() {
        super.lazyLoad()
        mViewModel.requestArticleList(pageNum, mSeriesBean.id)
    }
}