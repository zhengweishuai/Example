package com.example.edz.ui.adapter

import android.content.Context
import android.content.Intent
import com.example.edz.application.R
import com.example.edz.application.databinding.LayoutAdapterDiscoverArticleBinding
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.ui.activity.WebViewActivity

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class DiscoverArticleAdapter(val context: Context, id: Int) :
        BaseAdapter<ArticleListItemBean, LayoutAdapterDiscoverArticleBinding>(context, id) {
    override fun onBindVh(dataBinding: LayoutAdapterDiscoverArticleBinding?, holder: SupperViewHodel, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", mList[position].link)
            context.startActivity(intent)
        }
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_discover_article
}