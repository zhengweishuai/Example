package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.SeriesCollectBean
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request
import com.qmuiteam.qmui.widget.section.QMUISection
import java.util.*

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class SeriesViewModel : BaseViewModel() {
    val data = MutableLiveData<ArrayList<QMUISection<SeriesCollectBean, SeriesCollectBean>>>()
    private val list: ArrayList<QMUISection<SeriesCollectBean, SeriesCollectBean>> =
            ArrayList<QMUISection<SeriesCollectBean, SeriesCollectBean>>()

    fun requestSeriesData() {
        request({
            NetworkHelper.newInstance().tree()
        }, {
            it?.let {
                for (bean: SeriesCollectBean in it) {
                    val collectBeanChilds = bean.children
                    list.add(QMUISection(bean, collectBeanChilds))
                }
                data.postValue(list)
            }
        })
    }
}