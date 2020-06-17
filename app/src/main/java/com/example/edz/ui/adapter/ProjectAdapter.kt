package com.example.edz.ui.adapter

import android.content.Context
import com.example.edz.application.R
import com.example.edz.application.databinding.LayoutAdapterProjectItemBinding
import com.example.edz.bean.ProjectDetailBean

/**
 * author : zhengweishuai
 * date : 2020/6/17 0017.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectAdapter(context: Context, id: Int) :
        BaseAdapter<ProjectDetailBean, LayoutAdapterProjectItemBinding>(context, id) {
    override fun onBindVh(dataBinding: LayoutAdapterProjectItemBinding?, holder: SupperViewHodel, position: Int) {
        /*
        val targetW = DensityUtil.getScreenWidth(mContext) / 2 - DensityUtil.dip2px(mContext, 20f)
                val scale = resource.width / targetW
                resource.height
                dataBinding?.let {
                    val layoutParams = it.projectCover.layoutParams
                    layoutParams.width = targetW
                    layoutParams.height = resource.height / scale
                    it.projectCover.layoutParams = layoutParams
                }

         */
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_project_item
}