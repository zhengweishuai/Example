package com.example.edz.ui.activity

import android.content.Context
import android.content.Intent
import com.example.edz.R
import com.example.edz.databinding.ActivitySettingBindingImpl
import com.example.edz.viewmodel.SearchViewModel
import com.mvvm.BaseMvvmActivity
import kotlinx.android.synthetic.main.layout_base_title.*

/**
 * author : zhengweishuai
 * date : 2020/7/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ActivitySearch : BaseMvvmActivity<SearchViewModel, ActivitySettingBindingImpl>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ActivitySearch::class.java)
            context.startActivity(starter)
        }
    }

    override fun attachLayoutRes(): Int = R.layout.activity_search

    override fun initViews() {
        middle_title.text = "搜索"
    }

    override fun doListener() {
        rl_left.setOnClickListener { finish() }
    }

    override fun doBusiness() {

    }
}