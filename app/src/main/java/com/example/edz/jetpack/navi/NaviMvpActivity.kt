package com.example.edz.jetpack.navi

import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.base.BaseActivity
import com.example.edz.application.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class NaviMvpActivity : BaseActivity() {
    /*
    导航栏添加标签
     */
    private fun naviAddMark() {
        val menuView = navi_view.get(0) as BottomNavigationMenuView
        val itemView = menuView.get(0) as BottomNavigationItemView
        val mark = layoutInflater.inflate(R.layout.view_navi_mark, itemView, false)
        itemView.addView(mark)
    }

    override fun doBusiness() {

    }

    override fun doListener() {

    }

    override fun initViews() {
        val host = supportFragmentManager.findFragmentById(R.id.splash) as NavHostFragment
        val navController = host.navController
        navi_view.setupWithNavController(navController)
        naviAddMark()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }
}