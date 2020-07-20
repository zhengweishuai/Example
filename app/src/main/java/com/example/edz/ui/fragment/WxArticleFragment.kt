package com.example.edz.ui.fragment

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.constant.AppStaticRes
import com.example.edz.BR
import com.example.edz.R
import com.example.edz.databinding.FragmentWxArticleBinding
import com.example.edz.ui.activity.WebViewActivity
import com.example.edz.ui.adapter.DiscoverArticleAdapter
import com.example.edz.ui.adapter.DiscoverAuthorAdapter
import com.example.edz.viewmodel.WxArticleViewModel
import com.mvvm.BaseMvvmFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_wx_article.*

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：公众号
 */
class WxArticleFragment : BaseMvvmFragment<WxArticleViewModel, FragmentWxArticleBinding>() {
    private var mPosition: Int = -1
    private var pageNum = 1
    private var mAuthorId = -1
    override fun attachLayoutRes(): Int = R.layout.fragment_wx_article

    override fun initViews() {
        mDataBind.authorManager = LinearLayoutManager(requireActivity())
        mDataBind.authorAdapter = DiscoverAuthorAdapter(requireActivity(), BR.data).apply {
            mViewModel.authors.observe(this@WxArticleFragment, Observer {
                it?.let {
                    it[0].select = true
                    mAuthorId = it[0].id
                    setNewData(it)
                    mViewModel.requestArticles(mAuthorId, pageNum)
                }
            })
            mAuthor.observe(this@WxArticleFragment, Observer {
                mAuthorId = it.id
                swipe_refresh_view.autoRefresh()
            })
        }

        mDataBind.articleManager = LinearLayoutManager(requireActivity())
        mDataBind.articleAdapter = DiscoverArticleAdapter(requireActivity(),
                BR.data,
                itemClick = {
                    mPosition = it
                    WebViewActivity.start(this,
                            mDataBind.articleAdapter!!.mList[it].link,
                            mDataBind.articleAdapter!!.mList[it].id,
                            mDataBind.articleAdapter!!.mList[it].originId,
                            mDataBind.articleAdapter!!.mList[it].collect)
                }).apply {
            mViewModel.articles.observe(this@WxArticleFragment, Observer {
                swipe_refresh_view.finishRefresh()
                swipe_refresh_view.finishLoadMore()
                it.datas?.apply {
                    if (pageNum == 1) setNewData(this) else addList(this)
                }
                if (this.mList.size >= it.total) {
                    swipe_refresh_view.finishRefreshWithNoMoreData()
                }
            })
        }
    }

    override fun initListener() {
        swipe_refresh_view.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageNum++
                refreshLayout.resetNoMoreData()
                mViewModel.requestArticles(mAuthorId, pageNum)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageNum = 1
                mViewModel.requestArticles(mAuthorId, pageNum)
            }
        })
    }

    override fun doBusiness() {
    }

    override fun lazyLoad() {
        super.lazyLoad()
        mViewModel.requestAuthors()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppStaticRes.ARTICLE_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            data?.let { intent ->
                val isCollect = intent.getBooleanExtra("is_collect", false)
                if (!isCollect && mPosition != -1) {
                    mDataBind.articleAdapter?.let {
                        it.removeItem(mPosition)
                    }
                }
            }
        }
    }
}