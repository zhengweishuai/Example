package com.example.edz.ui.model

import com.qmuiteam.qmui.widget.section.QMUISection

/**
 * author : zhengweishuai
 * date : 2020/6/30 0030.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class SeriesHead(val text: String) : QMUISection.Model<SeriesHead> {
    override fun isSameItem(other: SeriesHead?): Boolean {
        return text == other?.text
    }

    override fun isSameContent(other: SeriesHead?): Boolean {
        return true
    }

    override fun cloneForDiff(): SeriesHead {
        return SeriesHead(text)
    }
}