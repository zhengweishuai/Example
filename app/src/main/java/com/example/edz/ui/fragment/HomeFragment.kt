package com.example.edz.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.edz.application.BR
import com.example.edz.application.R
import com.example.edz.application.databinding.FragmentHomeBinding
import com.example.edz.ui.activity.LoginActivity
import com.example.edz.ui.adapter.HomeArticleAdapter
import com.example.edz.ui.adapter.HomeBannerAdapter
import com.example.edz.ui.widget.PersionalPopup
import com.example.edz.utils.FixedSpeedScroller
import com.example.edz.utils.UserDataUtil
import com.example.edz.viewmodel.HomeViewModel
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupPosition
import com.mvvm.BaseMvvmFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_base_title.*


/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class HomeFragment : BaseMvvmFragment<HomeViewModel, FragmentHomeBinding>() {
    lateinit var mHandler: Handler
    private lateinit var mVpRun: Runnable
    private val vp_switch_duration = 3000L//viewpager切换时间间隔
    private val vp_slide_duration = 1000//viewpager滑动时间

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initViews() {
        mDataBind.vm = mViewModel
        iv_left.setBackgroundResource(R.drawable.home_persional_icon)
        middle_title.text = "玩安卓"
        try {
            val field = ViewPager::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            val scroller = FixedSpeedScroller(vp_banner.context)
            field.set(vp_banner, scroller)
            scroller.setmDuration(vp_slide_duration)
        } catch (e: Exception) {
        }
        //recyclerview
        mDataBind.manager = LinearLayoutManager(requireActivity())
        mDataBind.rvAdapter = HomeArticleAdapter(requireActivity(), br_id = BR.itemBeam).apply {
            mViewModel.articleData.observe(this@HomeFragment, Observer {
                addList(it)
            })
        }
        mVpRun = Runnable {
            val itemCount = mDataBind.bannerAdapter?.count
            itemCount?.let {
                if (it > 1) {
                    val currentItem = vp_banner.currentItem
                    vp_banner.setCurrentItem((currentItem + 1) % it, true)
                    mHandler?.let {
                        it.postDelayed(mVpRun, vp_switch_duration)
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        rl_left.setOnClickListener {
            if (UserDataUtil.getUserData() == null) {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
            } else {
                XPopup.Builder(requireActivity())
                        .popupPosition(PopupPosition.Left)
                        .hasStatusBarShadow(true)
                        .asCustom(PersionalPopup(requireActivity()))
                        .show()
            }
        }
        //vp
        mDataBind.bannerAdapter = HomeBannerAdapter(requireActivity()).apply {
            mViewModel.bannerData.observe(this@HomeFragment, Observer {
                this.addData(it)
                this.notifyDataSetChanged()
                //执行轮播
                mHandler = Handler()
                mHandler.postDelayed(mVpRun, vp_switch_duration)
            })
        }

        vp_banner.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mHandler.removeCallbacks(mVpRun)
                }
                MotionEvent.ACTION_UP -> {
                    mHandler.postDelayed(mVpRun, vp_switch_duration)
                }
                MotionEvent.ACTION_CANCEL -> {
                    mHandler.postDelayed(mVpRun, vp_switch_duration)
                }
            }
            false
        })
    }

    override fun doBusiness() {
        mViewModel.requestBanners()
        mViewModel.requestArticleList(1)
    }

    override fun onDestroy() {
        mHandler.removeCallbacks(mVpRun)
        super.onDestroy()
    }
}