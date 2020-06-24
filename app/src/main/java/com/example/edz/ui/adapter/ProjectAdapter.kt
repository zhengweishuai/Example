package com.example.edz.ui.adapter

import android.content.Context
import com.example.edz.R
import com.example.edz.databinding.LayoutAdapterProjectItemBinding
import com.example.edz.bean.ProjectDetailBean

/**
 * author : zhengweishuai
 * date : 2020/6/17 0017.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectAdapter(context: Context, id: Int,
                     val itemClick: (position: Int) -> Unit) :
        BaseAdapter<ProjectDetailBean, LayoutAdapterProjectItemBinding>(context, id) {
    override fun onBindVh(dataBinding: LayoutAdapterProjectItemBinding?, holder: SupperViewHodel, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick(position)
        }
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_project_item
}