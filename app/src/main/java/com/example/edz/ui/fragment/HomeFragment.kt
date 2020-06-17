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
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_base_title.*


/**
 * author : zhengweishuai
 * date : 2020/6/2 0002.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class HomeFragment : BaseMvvmFragment<HomeViewModel, FragmentHomeBinding>() {
    var mHandler = Handler()
    private val vp_switch_duration = 3000L//viewpager切换时间间隔
    private val vp_slide_duration = 1000//viewpager滑动时间
    private var pageNum: Int = 1

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
                if (refresh_view.isRefreshing) refresh_view.finishRefresh()
                if (refresh_view.isLoading) refresh_view.finishLoadMore()
                if (pageNum == 1) setNewData(it) else addList(it)
            })
        }
        //vp
        mDataBind.bannerAdapter = HomeBannerAdapter(requireActivity()).apply {
            mViewModel.bannerData.observe(this@HomeFragment, Observer {
                this.addData(it)
                this.notifyDataSetChanged()
                //执行轮播
                mHandler.postDelayed(mVpRun, vp_switch_duration)
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        refresh_view.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshLayout.resetNoMoreData()
                pageNum = 1
                mViewModel.requestBanners()
                mViewModel.requestArticleList(pageNum)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageNum++
                mViewModel.requestArticleList(pageNum)
            }
        })
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
        vp_banner.setOnTouchListener(OnTouchListener { _, event ->
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
        vp_banner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                vp_banner.adapter?.let {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        when (vp_banner.currentItem) {
                            it.count - 1 -> {
                                vp_banner.setCurrentItem(1, false);
                            }
                            0 -> {
                                vp_banner.setCurrentItem(it.count - 2, false);
                            }
                            else -> {
                                vp_banner.setCurrentItem(vp_banner.currentItem, true);
                            }
                        }
                    }
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    override fun doBusiness() {
        refresh_view.autoRefresh()
    }

    override fun onDestroy() {
        mHandler.removeCallbacks(mVpRun)
        super.onDestroy()
    }

    private var mVpRun: Runnable = object : Runnable {
        override fun run() {
            mDataBind.bannerAdapter?.let {
                val itemCount = it.count
                if (itemCount > 1) {
                    vp_banner?.let {
                        val currentItem = it.currentItem
                        it.currentItem = (currentItem + 1) % itemCount
                        mHandler.postDelayed(this, vp_switch_duration)
                    }
                }
            }
        }
    }
}