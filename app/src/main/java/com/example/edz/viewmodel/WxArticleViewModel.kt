package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.ArticleAuthorBean
import com.example.edz.bean.ArticleListItemBean
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class WxArticleViewModel : BaseViewModel() {
    var authors = MutableLiveData<ArrayList<ArticleAuthorBean>>()
    var articles = MutableLiveData<ArrayList<ArticleListItemBean>>()

    fun requestAuthors() {
        request({
            NetworkHelper.newInstance().wxarticle()
        }, {
            authors.postValue(it)
        }, {
            it.message?.let { it1 -> showToast(it1) }
        })
    }

    fun requestArticles(id: Int, page: Int) {
        request({
            val url = "wxarticle/list/$id/$page/json"
            NetworkHelper.newInstance().wxArticleHistory(url)
        }, {
            articles.postValue(it?.datas)
        }, {
            it.message?.let { it1 -> showToast(it1) }
        }, true)
    }
}