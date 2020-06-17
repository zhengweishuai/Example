package com.example.edz.ui.widget

import android.content.Context
import com.example.edz.R
import com.lxj.xpopup.core.CenterPopupView
import kotlinx.android.synthetic.main.layout_popup_custom.view.*

/**
 * author : zhengweishuai
 * date : 2020/6/10 0010.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：公共提示弹窗
 */
class CustomPopup(context: Context,
                  val leftClickListener: (CustomPopup) -> Unit,
                  val rightClickListener: (CustomPopup) -> Unit,
                  var title: String = "提示",
                  var content: String = "",
                  var left: String = "取消",
                  var right: String = "确定") : CenterPopupView(context) {

    override fun getImplLayoutId(): Int = R.layout.layout_popup_custom

    override fun onCreate() {
        super.onCreate()
        popup_title.text = title
        popup_content.text = content
        popup_left.text = left
        popup_right.text = right
        popup_left.setOnClickListener {
            leftClickListener(this)
            dismiss()
        }
        popup_right.setOnClickListener {
            rightClickListener(this)
            dismiss()
        }
    }

}