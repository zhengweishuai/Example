package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.bean.BannerBean
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request

/**
 * author : zhengweishuai
 * date : 2020/6/4 0004.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class HomeViewModel : BaseViewModel() {
    var bannerData = MutableLiveData<ArrayList<BannerBean>>()
    var articleData = MutableLiveData<ArrayList<ArticleListItemBean>>()

    fun requestBanners() {
        request({
            NetworkHelper.newInstance().banners()
        }, {
            it.add(it.size, it[0])
            it.add(0, it[it.size - 2])
            bannerData.postValue(it)
        }, {

        })
    }

    fun requestArticleList(pageNum: Int) {
        val url = "article/list/$pageNum/json"
        request({ NetworkHelper.newInstance().homeArticleList(url) },
                {
                    articleData.postValue(it.datas)
                },
                {

                })
    }
}