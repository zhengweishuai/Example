package com.example.edz.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edz.R
import com.example.edz.BR
import com.example.edz.databinding.FragmentWxArticleBinding
import com.example.edz.ui.adapter.DiscoverArticleAdapter
import com.example.edz.ui.adapter.DiscoverAuthorAdapter
import com.example.edz.viewmodel.WxArticleViewModel
import com.mvvm.BaseMvvmFragment

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：公众号
 */
class WxArticleFragment : BaseMvvmFragment<WxArticleViewModel, FragmentWxArticleBinding>() {
    private var pageNum = 1
    override fun attachLayoutRes(): Int = R.layout.fragment_wx_article

    override fun initViews() {
        mDataBind.authorManager = LinearLayoutManager(requireActivity())
        mDataBind.authorAdapter = DiscoverAuthorAdapter(requireActivity(), BR.data).apply {
            mViewModel.authors.observe(this@WxArticleFragment, Observer {
                it?.let {
                    it[0].select = true
                    setNewData(it)
                    mViewModel.requestArticles(it[0].id, pageNum)
                }
            })
            mAuthor.observe(this@WxArticleFragment, Observer {
                mViewModel.requestArticles(it.id, pageNum)
            })
        }

        mDataBind.articleManager = LinearLayoutManager(requireActivity())
        mDataBind.articleAdapter = DiscoverArticleAdapter(requireActivity(), BR.data).apply {
            mViewModel.articles.observe(this@WxArticleFragment, Observer {
                setNewData(it)
            })
        }
    }

    override fun initListener() {
    }

    override fun doBusiness() {
        mViewModel.requestAuthors()
    }
}