package com.example.edz.utils

import androidx.recyclerview.widget.RecyclerView

/**
 * author : zhengweishuai
 * date : 2020/6/23 0023.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
abstract class EndlessRecyclerOnScrollListener :RecyclerView.OnScrollListener(){
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!recyclerView.canScrollVertically(1) && dy > 0) {
            onLoadMore()
        }
    }

    /**
     * 加载更多回调
     */
    abstract fun onLoadMore()
}