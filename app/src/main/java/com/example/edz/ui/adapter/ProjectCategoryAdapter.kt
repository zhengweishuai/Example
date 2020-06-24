package com.example.edz.ui.adapter

import android.content.Context
import com.example.edz.R
import com.example.edz.bean.ProjectCategoryBean
import com.example.edz.databinding.LayoutScreenProjectItemBinding

/**
 * author : zhengweishuai
 * date : 2020/6/24 0024.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectCategoryAdapter(context: Context,
                             id: Int,
                             val itemClick: (ProjectCategoryBean) -> Unit) :
        BaseAdapter<ProjectCategoryBean, LayoutScreenProjectItemBinding>(context, id) {


    override fun ItemLayoutId(): Int = R.layout.layout_screen_project_item

    override fun onBindVh(dataBinding: LayoutScreenProjectItemBinding?, holder: SupperViewHodel, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick(mList[position])
        }
    }
}