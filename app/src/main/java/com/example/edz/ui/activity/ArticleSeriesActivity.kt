package com.example.edz.ui.activity

import android.content.Context
import android.content.Intent
import com.example.edz.R
import com.example.edz.bean.SeriesCollectBean
import com.example.edz.databinding.ActivityArticleSeriesBinding
import com.example.edz.ui.adapter.SeriesTypePageAdapter
import com.example.edz.viewmodel.ArticleSeriesViewModel
import com.mvvm.BaseMvvmActivity
import kotlinx.android.synthetic.main.activity_article_series.*
import kotlinx.android.synthetic.main.layout_base_title.*

/**
 * author : zhengweishuai
 * date : 2020/7/1 0001.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ArticleSeriesActivity : BaseMvvmActivity<ArticleSeriesViewModel, ActivityArticleSeriesBinding>() {

    companion object {
        /*
        series ：文章系列集合
        currentSeries：当前点击的文章系列
         */
        fun start(context: Context, series: SeriesCollectBean, currentSeries: SeriesCollectBean? = null) {
            val starter = Intent(context, ArticleSeriesActivity::class.java)
            starter.putExtra("SeriesSection", series)
            starter.putExtra("CurrentSection", currentSeries)
            context.startActivity(starter)
        }
    }


    override fun attachLayoutRes(): Int = R.layout.activity_article_series

    override fun initViews() {
        val series: SeriesCollectBean = intent.getSerializableExtra("SeriesSection") as SeriesCollectBean
        val currentSection: SeriesCollectBean? = intent.getSerializableExtra("CurrentSection") as SeriesCollectBean
        middle_title.text = series.name
        mDataBind.vm = mViewModel
        currentSection?.let {
            for (i in series.children.indices) {
                tb_layout.addTab(tb_layout.newTab())
                tb_layout.getTabAt(i)?.text = series.children[i].name
            }
            mDataBind.adapter = SeriesTypePageAdapter(supportFragmentManager, series.children)
            tb_layout.setupWithViewPager(vp, false)
            vp.offscreenPageLimit = 3
        }
        series.children.forEachIndexed { index, seriesCollectBean ->
            if (seriesCollectBean.id == currentSection?.id) {
                tb_layout.getTabAt(index)?.select()
            }
        }
    }

    override fun doListener() {
        rl_left.setOnClickListener { finish() }
    }

    override fun doBusiness() {

    }
}