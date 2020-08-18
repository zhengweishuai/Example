package com.example.edz.ui.widget

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.example.edz.R
import com.lxj.xpopup.animator.PopupAnimator
import com.lxj.xpopup.animator.ScaleAlphaAnimator
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation
import kotlinx.android.synthetic.main.layout_popup_custom.view.*

/**
 * author : zhengweishuai
 * date : 2020/6/10 0010.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：公共提示弹窗
 */
class CustomPopup(context: Context): BasePopupView(context) {

    override fun getPopupLayoutId(): Int = R.layout.layout_popup_custom

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        translationY = 0f
    }

    override fun getPopupAnimator(): PopupAnimator? {
        return ScaleAlphaAnimator(popupContentView, PopupAnimation.ScaleAlphaFromCenter)
    }

    fun setTitle(title: String): CustomPopup {
        popup_title.visibility = if (TextUtils.isEmpty(title)) View.GONE else View.VISIBLE
        popup_title.text = title
        return this
    }

    fun setContent(content: String): CustomPopup {
        popup_content.visibility = if (TextUtils.isEmpty(content)) View.GONE else View.VISIBLE
        popup_content.text = content
        return this
    }

    fun setLeftBtn(left: String, leftClickListener: (CustomPopup) -> Unit): CustomPopup {
        popup_left.visibility = if (TextUtils.isEmpty(left)) View.GONE else View.VISIBLE
        popup_left.text = left
        popup_left.setOnClickListener {
            leftClickListener(this)
            dismiss()
        }
        return this
    }


    fun setRightBtn(right: String, rightClickListener: (CustomPopup) -> Unit): CustomPopup {
        popup_right.visibility = if (TextUtils.isEmpty(right)) View.GONE else View.VISIBLE
        popup_right.text = right
        popup_right.setOnClickListener {
            rightClickListener(this)
            dismiss()
        }
        return this
    }

}