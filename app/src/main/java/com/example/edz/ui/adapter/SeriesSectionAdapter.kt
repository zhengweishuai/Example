package com.example.edz.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.edz.R
import com.example.edz.bean.SeriesCollectBean
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter
import com.qmuiteam.qmui.widget.section.QMUISection
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter

/**
 * author : zhengweishuai
 * date : 2020/6/30 0030.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class SeriesSectionAdapter(val onHeadClick: (SeriesCollectBean) -> Unit,
                           val onSectionClick: (SeriesCollectBean, SeriesCollectBean) -> Unit)
    : QMUIDefaultStickySectionAdapter<SeriesCollectBean, SeriesCollectBean>() {
    override fun onCreateSectionItemViewHolder(viewGroup: ViewGroup): ViewHolder {
        val itemView = View.inflate(viewGroup.context, R.layout.layout_series_item_section, null)
        return SectionViewHolder(itemView)
    }

    override fun onCreateSectionHeaderViewHolder(viewGroup: ViewGroup): HeadViewHolder {
        val itemView = View.inflate(viewGroup.context, R.layout.layout_series_item_head, null)
        return HeadViewHolder(itemView)
    }

    override fun onBindSectionHeader(holder: ViewHolder?, position: Int, section: QMUISection<SeriesCollectBean, SeriesCollectBean>?) {
        val headVH = holder as HeadViewHolder
        headVH.headTitle?.text = section?.header?.name
        headVH.headTitle?.setOnClickListener {
            onHeadClick(section!!.header)
        }
    }

    override fun onBindSectionItem(holder: ViewHolder?, position: Int, section: QMUISection<SeriesCollectBean, SeriesCollectBean>?, itemIndex: Int) {
        val sectionVH = holder as SectionViewHolder
        sectionVH.sectionTv?.text = section?.getItemAt(itemIndex)?.name
        sectionVH.sectionTv?.setOnClickListener {
            val mSection = section!!.getItemAt(itemIndex)
            onSectionClick(section.header, mSection)
        }
    }

    inner class HeadViewHolder(itemView: View?) : QMUIStickySectionAdapter.ViewHolder(itemView) {
        var headTitle: TextView? = null
        var contentLayout: ConstraintLayout? = null

        init {
            itemView?.let {
                headTitle = itemView.findViewById(R.id.head_title)
                contentLayout = itemView.findViewById(R.id.content_layout)
            }
        }
    }

    inner class SectionViewHolder(itemView: View?) : QMUIStickySectionAdapter.ViewHolder(itemView) {
        var sectionTv: TextView? = null

        init {
            itemView?.let {
                sectionTv = itemView.findViewById(R.id.section_tv)
            }
        }
    }
}