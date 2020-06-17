package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.ProjectDetailBean
import com.mvvm.vm.BaseViewModel
import com.mvvm.vm.request

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectViewModel : BaseViewModel() {
    val projectList = MutableLiveData<ArrayList<ProjectDetailBean>>()
    fun requestProjectList() {
        request({
            NetworkHelper.newInstance().projectList()
        }, {
            projectList.postValue(it.datas)
        }, {

        }, showLoading = true)
    }
}