package com.example.edz.bean

import com.qmuiteam.qmui.widget.section.QMUISection
import java.io.Serializable

/**
 * author : zhengweishuai
 * date : 2020/6/30 0030.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
data class SeriesCollectBean(val courseId: Int,
                             val id: Int,
                             val name: String,
                             val children: ArrayList<SeriesCollectBean>
) : QMUISection.Model<SeriesCollectBean>, Serializable {
    override fun isSameItem(other: SeriesCollectBean?): Boolean {
        return this.id == other?.id
    }

    override fun isSameContent(other: SeriesCollectBean?): Boolean = true

    override fun cloneForDiff(): SeriesCollectBean {
        return SeriesCollectBean(courseId, id, name, children)
    }
}