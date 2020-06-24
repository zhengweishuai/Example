package com.example.edz.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.edz.api.NetworkHelper
import com.example.edz.bean.BaseListResponse
import com.example.edz.bean.ProjectCategoryBean
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
    val projectCategory = MutableLiveData<ArrayList<ProjectCategoryBean>>()
    val projectList = MutableLiveData<BaseListResponse<ProjectDetailBean>>()
    fun requestProjectList(pageNum: Int, id: Int) {
        request({
            NetworkHelper.newInstance().projectList(pageNum, id)
        }, {
            projectList.postValue(it)
        })
    }

    fun requestProjectCategory() {
        request({
            NetworkHelper.newInstance().projectCategory()
        }, {
            projectCategory.postValue(it)
        })
    }
}