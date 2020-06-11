package com.example.edz.ui.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide


object CustomBindAdapter {
    /**
     * glide加载图片
     */
    @BindingAdapter(value = ["imageUrl", "error", "placeHolder"], requireAll = false)
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?, error: Drawable?, placeHolder: Drawable?) {
        Glide.with(imageView.context)
                .load(url)
                .placeholder(placeHolder)
                .error(error)
                .centerCrop()
                .into(imageView)
    }

    /**
     * recyclerview
     */
    @JvmStatic
    @BindingAdapter(value = ["adapter", "layoutManager"], requireAll = false)
    fun setRLAdapter(recyclerView: RecyclerView, adapter: BaseAdapter<*, *>, layoutManager: RecyclerView.LayoutManager) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    /**
     * viewpager
     */
    @JvmStatic
    @BindingAdapter(value = ["adapter"], requireAll = false)
    fun setVPAdapter(vp: ViewPager, adapter: PagerAdapter) {
        vp.adapter = adapter
    }
}