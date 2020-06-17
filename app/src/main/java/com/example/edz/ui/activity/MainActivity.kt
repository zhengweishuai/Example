package com.example.edz.ui.activity

import android.view.View
import androidx.core.content.ContextCompat
import com.example.edz.R
import com.example.edz.databinding.ActivitySplashBinding
import com.example.edz.ui.fragment.DiscoverFragment
import com.example.edz.ui.fragment.HomeFragment
import com.example.edz.ui.fragment.OtherFragment
import com.mvvm.BaseMvvmActivity
import com.mvvm.BaseMvvmFragment
import com.mvvm.vm.BaseViewModel
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class MainActivity : BaseMvvmActivity<BaseViewModel, ActivitySplashBinding>(), View.OnClickListener {
    private val homeFragment by lazy {
        HomeFragment()
    }
    private val discoverFragment by lazy {
        DiscoverFragment()
    }
    private val otherFragment by lazy {
        OtherFragment()
    }
    private var currentFragment: BaseMvvmFragment<*, *>? = null

    override fun attachLayoutRes(): Int = R.layout.activity_splash

    override fun initViews() {
        rb_home.apply {
            isChecked = true
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.color_d81e06))
        }
        supportFragmentManager.beginTransaction().add(R.id.content_layout, homeFragment)
                .show(homeFragment)
                .commitAllowingStateLoss()
        currentFragment = homeFragment
    }

    override fun doListener() {
        rb_home.setOnClickListener(this)
        rb_discover.setOnClickListener(this)
        rb_other.setOnClickListener(this)
    }

    override fun doBusiness() {

    }

    private fun resetRbTv() {
        rb_home.setTextColor(ContextCompat.getColor(this, R.color.color_666666))
        rb_discover.setTextColor(ContextCompat.getColor(this, R.color.color_666666))
        rb_other.setTextColor(ContextCompat.getColor(this, R.color.color_666666))
    }

    override fun onClick(v: View?) {
        resetRbTv()
        when (v?.id) {
            R.id.rb_home -> {
                rb_home.setTextColor(ContextCompat.getColor(this, R.color.color_d81e06))
                currentFragment?.let { switchFragment(it, homeFragment) }
            }
            R.id.rb_discover -> {
                rb_discover.setTextColor(ContextCompat.getColor(this, R.color.color_d81e06))
                currentFragment?.let { switchFragment(it, discoverFragment) }
            }
            R.id.rb_other -> {
                rb_other.setTextColor(ContextCompat.getColor(this, R.color.color_d81e06))
                currentFragment?.let { switchFragment(it, otherFragment) }
            }
        }
    }

    private fun switchFragment(fromFragment: BaseMvvmFragment<*, *>, toFragment: BaseMvvmFragment<*, *>) {
        if (toFragment.isAdded) {
            supportFragmentManager.beginTransaction().hide(fromFragment).show(toFragment).commitAllowingStateLoss()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.content_layout, toFragment)
                    .hide(fromFragment)
                    .show(toFragment)
                    .commitAllowingStateLoss()
        }
        currentFragment = toFragment
    }
}