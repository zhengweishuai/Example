package com.example.edz.ui.adapter

import android.content.Context
import com.example.edz.R
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.databinding.LayoutAdapterHomeArticleBinding

/**
 * author : zhengweishuai
 * date : 2020/6/11 0011.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class HomeArticleAdapter(var context: Context,
                         br_id: Int,
                         val onItemClick: (position: Int) -> Unit = {}) :
        BaseAdapter<ArticleListItemBean, LayoutAdapterHomeArticleBinding>(context, br_id) {
    override fun onBindVh(dataBinding: LayoutAdapterHomeArticleBinding?, holder: SupperViewHodel, position: Int) {
        dataBinding?.adapterClick = AdapterClick(position)
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_home_article

    inner class AdapterClick(private val position: Int) {
        fun itemClick() {
            onItemClick(position)
        }
    }
}