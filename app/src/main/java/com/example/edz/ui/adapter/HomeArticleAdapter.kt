package com.example.edz.ui.adapter

import android.content.Context
import android.content.Intent
import com.example.edz.application.R
import com.example.edz.application.databinding.LayoutAdapterHomeArticleBinding
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.ui.activity.WebViewActivity

/**
 * author : zhengweishuai
 * date : 2020/6/11 0011.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class HomeArticleAdapter(var context: Context, br_id: Int) :
        BaseAdapter<ArticleListItemBean, LayoutAdapterHomeArticleBinding>(context, br_id) {
    override fun onBindVh(dataBinding: LayoutAdapterHomeArticleBinding?, holder: SupperViewHodel, position: Int) {
        dataBinding?.adapterClick = AdapterClick(position)
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_home_article

    inner class AdapterClick(private val position: Int) {
        fun itemClick() {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", mList[position].link)
            context.startActivity(intent)
        }
    }
}