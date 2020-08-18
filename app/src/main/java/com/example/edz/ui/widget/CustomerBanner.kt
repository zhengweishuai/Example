package com.example.edz.ui.widget

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.example.edz.R
import com.example.edz.bean.BannerBean
import com.example.edz.ui.adapter.HomeBannerAdapter
import com.example.edz.utils.FixedSpeedScroller
import com.utils.LogUtil
import kotlinx.android.synthetic.main.layout_banner.view.*

/**
 * author : zhengweishuai
 * date : 2020/6/24 0024.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：简单banner
 */
class CustomerBanner : FrameLayout, OnTouchListener, ViewPager.OnPageChangeListener {
    lateinit var homeBannerAdapter: HomeBannerAdapter
    var mHandler = Handler()
    private val vp_switch_duration = 3000L//viewpager切换时间间隔
    private val vp_slide_duration = 1000//viewpager滑动时间
    private var isPlay = false

    //banner高度
    private var mHeight: Int = 200

    //banner距离屏幕左右边距
    private var mLeftRightSpace: Int = 15

    //小圆点直径
    private var mPointWidth: Int = 5

    //小圆点间距
    private var mPointSpace: Int = 10

    //小圆点默认颜色
    private var mPointDefaultColor: Int = Color.parseColor("#f2f4f5")

    //小圆点选中颜色
    private var mPointSelectColor: Int = Color.parseColor("#d81e06")

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        retrieveAttributes(context, attrs)
        init(context)
    }

    private fun retrieveAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.cus_banner)
            mHeight = ta.getDimensionPixelSize(R.styleable.cus_banner_height, 150)
            mLeftRightSpace = ta.getDimensionPixelSize(R.styleable.cus_banner_left_right_space, 15)
            mPointWidth = ta.getDimensionPixelSize(R.styleable.cus_banner_point_width, 5)
            mPointSpace = ta.getDimensionPixelSize(R.styleable.cus_banner_point_space, 10)
            mPointDefaultColor = ta.getColor(R.styleable.cus_banner_point_default_color, Color.GRAY)
            mPointSelectColor = ta.getColor(R.styleable.cus_banner_point_select_color, Color.RED)
            ta.recycle()
        }
    }

    fun init(context: Context) {
        val mRoot = View.inflate(context, R.layout.layout_banner, null)
        addView(mRoot, LayoutParams(LayoutParams.MATCH_PARENT, mHeight))
        try {
            val field = ViewPager::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            val scroller = FixedSpeedScroller(vp_banner.context)
            field.set(vp_banner, scroller)
            scroller.setmDuration(vp_slide_duration)
        } catch (e: Exception) {
        }
        homeBannerAdapter = HomeBannerAdapter(context)
        vp_banner.adapter = homeBannerAdapter
        vp_banner.addOnPageChangeListener(this)
        vp_banner.setOnTouchListener(this)
    }

    /*
    填充banner数据
     */
    fun setData(list: MutableList<BannerBean>) {
        ll_layout_point.removeAllViews()
        for (i in 1..list.size) {
            val view = View(context)
            val params = LinearLayout.LayoutParams(mPointWidth, mPointWidth)
            params.leftMargin = mPointSpace
            params.rightMargin = mPointSpace
            view.setBackgroundColor(if (i == 0) mPointSelectColor else mPointDefaultColor)
            view.layoutParams = params
            ll_layout_point.addView(view)
        }
        if (list.size > 1) {
            //添加第一个item到list底部
            list.add(list.size, list[0])
            //添加最后一个item到list顶部
            list.add(0, list[list.size - 2])
        }
        homeBannerAdapter.addData(list)
        homeBannerAdapter.notifyDataSetChanged()
        if (list.size > 1) {
            vp_banner.setCurrentItem(1, false);
            onStart()
        }
    }

    /*
    手指触摸到view时，停止banner自动切换，手指离开时再启动自动切换
     */
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
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
        return false
    }

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
        vp_banner.adapter?.let {
            val count = ll_layout_point.childCount
            var mPosition = position
            when (position) {
                0 -> {
                    mPosition = it.count - 3
                }
                it.count - 1 -> {
                    mPosition = 0
                }
                else -> {
                    mPosition -= 1
                }
            }
            for (p in 0 until count) {
                val child: View = ll_layout_point.getChildAt(p)
                if (p == mPosition) {
                    child.setBackgroundColor(mPointSelectColor)
                } else {
                    child.setBackgroundColor(mPointDefaultColor)
                }
            }
        }
    }

    /*
    停止轮播
     */
    fun onStop() {
        isPlay = false
        mHandler.removeCallbacks(mVpRun)
    }

    /*
    启动轮播
     */
    fun onStart() {
        isPlay = true
        mHandler.removeCallbacks(mVpRun)
        mHandler.postDelayed(mVpRun, vp_switch_duration)
    }

    /*
    是否在轮播
     */
    fun isPlay(): Boolean = isPlay

    /*
    计时器
     */
    private var mVpRun: Runnable = object : Runnable {
        override fun run() {
            homeBannerAdapter?.let { bannerAdapter ->
                val itemCount = bannerAdapter.count
                if (itemCount > 1) {
                    vp_banner?.let {
                        val currentItem = it.currentItem
                        it.currentItem = (currentItem + 1) % itemCount
                    }
                }
                mHandler.postDelayed(this, vp_switch_duration)
            }
        }
    }
}