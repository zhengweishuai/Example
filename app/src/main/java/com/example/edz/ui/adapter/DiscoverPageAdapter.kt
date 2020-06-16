package com.example.edz.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.edz.ui.fragment.ProjectFragment
import com.example.edz.ui.fragment.SeriesFragment
import com.example.edz.ui.fragment.WxArticleFragment

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class DiscoverPageAdapter(support: FragmentManager,
                          private val tabs: ArrayList<String>) :
        FragmentStatePagerAdapter(support, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WxArticleFragment()
            }
            1 -> {
                SeriesFragment()
            }
            2 -> {
                ProjectFragment()
            }
            else -> WxArticleFragment()
        }
    }

    override fun getCount(): Int = tabs.size

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }
}