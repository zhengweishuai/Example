package com.example.edz.ui.model

import com.qmuiteam.qmui.widget.section.QMUISection

/**
 * author : zhengweishuai
 * date : 2020/6/30 0030.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class SeriesItem(val text: String) : QMUISection.Model<SeriesItem> {
    override fun isSameItem(other: SeriesItem?): Boolean {
        return text == other?.text
    }

    override fun isSameContent(other: SeriesItem?): Boolean {
        return true
    }

    override fun cloneForDiff(): SeriesItem {
        return SeriesItem(text)
    }
}