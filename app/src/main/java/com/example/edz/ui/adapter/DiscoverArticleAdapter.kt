package com.example.edz.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.edz.R
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.databinding.LayoutAdapterDiscoverArticleBinding
import com.example.edz.ui.activity.WebViewActivity

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class DiscoverArticleAdapter(val context: Context, id: Int, private val isUserSwiped: Boolean = false) :
        BaseAdapter<ArticleListItemBean, LayoutAdapterDiscoverArticleBinding>(context, id) {
    var deletePos = MutableLiveData<Int>()

    override fun onBindVh(dataBinding: LayoutAdapterDiscoverArticleBinding?, holder: SupperViewHodel, position: Int) {
        dataBinding?.let {
            it.contentLayout.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("articleBean", mList[position])
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
            it.swipeMenu.isSwipeEnable = isUserSwiped
            it.tvDelete.setOnClickListener {
                deletePos.postValue(position)
            }
        }
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_discover_article
}