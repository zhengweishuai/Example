package com.example.edz.ui.fragment

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edz.R
import com.example.edz.bean.SeriesCollectBean
import com.example.edz.databinding.FragmentSeriesBinding
import com.example.edz.ui.activity.ArticleSeriesActivity
import com.example.edz.ui.adapter.SeriesSectionAdapter
import com.example.edz.viewmodel.SeriesViewModel
import com.mvvm.BaseMvvmFragment
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.section.QMUISection
import kotlinx.android.synthetic.main.fragment_series.*
import java.util.*

/**
 * author : zhengweishuai
 * date : 2020/6/15 0015.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：体系
 */
class SeriesFragment : BaseMvvmFragment<SeriesViewModel, FragmentSeriesBinding>() {
    private val list: ArrayList<QMUISection<SeriesCollectBean, SeriesCollectBean>> =
            ArrayList<QMUISection<SeriesCollectBean, SeriesCollectBean>>()

    override fun attachLayoutRes(): Int = R.layout.fragment_series

    override fun initViews() {
        val layoutManager = GridLayoutManager(requireActivity(), 3)
        val adapter = SeriesSectionAdapter(onHeadClick = {
            ArticleSeriesActivity.start(requireActivity(), it)
        }, onSectionClick = { head, section ->
            ArticleSeriesActivity.start(requireActivity(), head, section)
        })
        section_layout.setLayoutManager(layoutManager)
        section_layout.setAdapter(adapter)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.getItemIndex(position) < 0) layoutManager.spanCount else 1
            }
        }
        mViewModel.data.observe(this, androidx.lifecycle.Observer {
            list.addAll(it)
            adapter.setData(list)
        })
        section_layout.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val margin = QMUIDisplayHelper.dp2px(context, 5)
                outRect.set(margin, margin, margin, margin)
            }
        })
    }

    override fun initListener() {
    }

    override fun doBusiness() {
        mViewModel.requestSeriesData()
    }
}