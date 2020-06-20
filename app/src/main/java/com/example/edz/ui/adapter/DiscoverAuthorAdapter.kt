package com.example.edz.ui.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.edz.R
import com.example.edz.databinding.LayoutAdapterDiscoverArticleAuthorBinding
import com.example.edz.bean.ArticleAuthorBean

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class DiscoverAuthorAdapter(val context: Context, id: Int) :
        BaseAdapter<ArticleAuthorBean, LayoutAdapterDiscoverArticleAuthorBinding>(context, id) {
    var clickItemPosition: Int = 0
    var mAuthor = MutableLiveData<ArticleAuthorBean>()
    override fun onBindVh(dataBinding: LayoutAdapterDiscoverArticleAuthorBinding?, holder: SupperViewHodel, position: Int) {
        holder.itemView.setBackgroundColor(
                if (mList[position].select) ContextCompat.getColor(context, R.color.white)
                else ContextCompat.getColor(context, R.color.color_f2f4f5))
        holder.itemView.setOnClickListener {
            if (clickItemPosition != position){
                mList[clickItemPosition].select = false
                mList[position].select = true
                clickItemPosition = position
                notifyDataSetChanged()
                mAuthor.postValue(mList[position])
            }
        }
    }

    override fun ItemLayoutId(): Int = R.layout.layout_adapter_discover_article_author
}