package com.example.edz.ui.widget

import android.content.Context
import android.view.View
import com.example.edz.application.R
import com.example.edz.utils.UserDataUtil
import com.gyf.immersionbar.ImmersionBar
import com.lxj.xpopup.core.DrawerPopupView
import kotlinx.android.synthetic.main.layout_popup_persional.view.*

/**
 * author : zhengweishuai
 * date : 2020/6/10 0010.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：侧边栏个人中心
 */
class PersionalPopup(context: Context) : DrawerPopupView(context) {
    override fun getImplLayoutId(): Int = R.layout.layout_popup_persional

    override fun onCreate() {
        super.onCreate()
        UserDataUtil.getUserData()?.let {
            user_name.text = it.nickname
        }
    }

    fun toCollect(view: View) {

    }
}