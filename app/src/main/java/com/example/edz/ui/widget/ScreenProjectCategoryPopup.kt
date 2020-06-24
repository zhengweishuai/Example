package com.example.edz.ui.widget

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edz.BR
import com.example.edz.R
import com.example.edz.bean.ProjectCategoryBean
import com.example.edz.ui.adapter.ProjectCategoryAdapter
import com.lxj.xpopup.impl.PartShadowPopupView
import kotlinx.android.synthetic.main.layout_screen_project_popup.view.*


/**
 * author : zhengweishuai
 * date : 2020/6/24 0024.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：筛选项目类别
 */
class ScreenProjectCategoryPopup(context: Context,
                                 private val arrayList: ArrayList<ProjectCategoryBean>,
                                 val categoryListener: (ProjectCategoryBean) -> Unit) : PartShadowPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.layout_screen_project_popup
    }

    override fun onCreate() {
        super.onCreate()
        val linearLayoutManager = LinearLayoutManager(context)
        rl_category.layoutManager = linearLayoutManager
        rl_category.adapter = ProjectCategoryAdapter(context,
                BR.category,
                itemClick = {
                    categoryListener(it)
                    dismiss()
                }).apply {
            setNewData(arrayList)
        }
    }
}