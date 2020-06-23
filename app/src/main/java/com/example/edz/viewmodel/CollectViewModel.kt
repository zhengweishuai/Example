package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.constant.NetUrls
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.ArticleListItemBean
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request

/**
 * author : zhengweishuai
 * date : 2020/6/18 0018.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class CollectViewModel : BaseViewModel() {

    var articles = MutableLiveData<ArrayList<ArticleListItemBean>>()

    fun requsetCollectArticle(pageNum: Int) {
        request({
            val url = NetUrls.COLLECT_ARTICLE_LIST + pageNum + "/json"
            NetworkHelper.newInstance().collectArticleList(url)
        }, {
            articles.postValue(it?.datas)
        }, showLoading = true, showToast = true)
    }

    fun cancleCollectArticle(id: Int, orginId: Int) {
        request({
            NetworkHelper.newInstance().cancelCollectArticle(id, orginId)
        })
    }
}