package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.HomeArticleListResponse
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request

/**
 * author : zhengweishuai
 * date : 2020/7/1 0001.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ArticleSeriesListViewModel : BaseViewModel() {
    var articleData = MutableLiveData<HomeArticleListResponse>()
    fun requestArticleList(pageNum: Int, cid: Int) {
        request({ NetworkHelper.newInstance().treeList(pageNum, cid) },
                {
                    articleData.postValue(it)
                }, showLoading = true)
    }
}