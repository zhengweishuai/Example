package com.example.edz.ui.adapter

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.edz.R
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.databinding.LayoutAdapterDiscoverArticleBinding

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class DiscoverArticleAdapter(val context: Context,
                             id: Int,
                             private val isCollectArt: Boolean = false,
                             val itemClick: (position: Int) -> Unit) :
        BaseAdapter<ArticleListItemBean, LayoutAdapterDiscoverArticleBinding>(context, id) {
    var deletePos = MutableLiveData<Int>()

    override fun onBindVh(dataBinding: LayoutAdapterDiscoverArticleBinding?, holder: SupperViewHodel, position: Int) {
        dataBinding?.let {
            it.contentLayout.setOnClickListener {
                itemClick(position)
            }
            it.swipeMenu.isSwipeEnable = isCollectArt
            it.tvDelete.setOnClickListener {
                deletePos.postValue(position)
            }
        }
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_discover_article
}