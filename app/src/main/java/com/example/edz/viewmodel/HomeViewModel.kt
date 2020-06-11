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
    var bannerData = MutableLiveData<MutableList<BannerBean>>()
    var articleData = MutableLiveData<MutableList<ArticleListItemBean>>()

    fun requestBanners() {
        request({
            NetworkHelper.newInstance().banners()
        }, {
            bannerData.postValue(it as MutableList<BannerBean>?)
        }, {

        })
    }

    fun requestArticleList(pageNum: Int) {
        var url = "article/list/" + pageNum + "0/json"
        request({ NetworkHelper.newInstance().homeArticleList(url) },
                {
                    articleData.postValue(it.datas)
                },
                {

                })
    }

}