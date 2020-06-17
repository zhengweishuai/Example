package com.example.edz.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.edz.R
import com.example.edz.bean.BannerBean

/**
 * author : zhengweishuai
 * date : 2020/6/10 0010.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class HomeBannerAdapter(private val context: Context) : PagerAdapter() {
    var mList = mutableListOf<BannerBean>()
    fun addData(list: MutableList<BannerBean>) {
        this.mList = list
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = mList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mView = LayoutInflater.from(context).inflate(R.layout.layout_adapter_home_banner, null).also {
            Glide.with(context).load(mList[position].imagePath).into(it.findViewById(R.id.banner_item))
        }
        container.addView(mView)
        return mView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}