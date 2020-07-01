package com.example.edz.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.edz.bean.SeriesCollectBean
import com.example.edz.ui.fragment.ArticleSeriesListFragment

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class SeriesTypePageAdapter(support: FragmentManager,
                            private val seriesList: ArrayList<SeriesCollectBean>) :
        FragmentStatePagerAdapter(support, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return ArticleSeriesListFragment.newInstance(seriesList[position])
    }

    override fun getCount(): Int = seriesList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return seriesList[position].name
    }
}