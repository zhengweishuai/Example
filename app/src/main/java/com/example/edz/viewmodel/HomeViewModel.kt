package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.bean.BannerBean
import com.example.edz.bean.HomeArticleListResponse
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
    var articleData = MutableLiveData<HomeArticleListResponse>()

    fun requestBanners() {
        request({
            NetworkHelper.newInstance().banners()
        }, {
            it?.let {
                bannerData.postValue(it)
            }
        }, {

        })
    }

    fun requestArticleList(pageNum: Int) {
        request({ NetworkHelper.newInstance().homeArticleList(pageNum) },
                {
                    articleData.postValue(it)
                })
    }
}