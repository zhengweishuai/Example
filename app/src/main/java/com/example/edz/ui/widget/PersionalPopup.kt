package com.example.edz.ui.widget

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import com.example.edz.R
import com.example.edz.ui.activity.CollectActivity
import com.example.edz.ui.activity.MainActivity
import com.example.edz.ui.activity.SettingActivity
import com.example.edz.utils.FixDexUtil
import com.example.edz.utils.UserDataUtil
import com.lxj.xpopup.core.DrawerPopupView
import com.widget.toast.ToastUtils
import kotlinx.android.synthetic.main.layout_popup_persional.view.*
import java.io.File


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
        collection.setOnClickListener {
            context.startActivity(Intent(context, CollectActivity::class.java))
        }
        share.setOnClickListener {
            initFix()
        }
        integral.setOnClickListener {
            ToastUtils.show("bug已经修复了")
        }
        setting.setOnClickListener {
            context.startActivity(Intent(context, SettingActivity::class.java))
            dismiss()
        }
    }

    fun initFix() {
        val externalStorageDirectory: File = Environment.getExternalStorageDirectory()
        // 遍历所有的修复dex , 因为可能是多个dex修复包
        val fileDir: File =
                if (externalStorageDirectory != null) File(externalStorageDirectory, "007")
                else File(context.filesDir, FixDexUtil.DEX_DIR) // data/user/0/包名/files/odex（这个可以任意位置）
        if (!fileDir.exists()) {
            fileDir.mkdirs()
        }
        if (FixDexUtil.isGoingToFix(context)) {
            FixDexUtil.loadFixedDex(context, Environment.getExternalStorageDirectory())
            ToastUtils.show("正在热修复----")
        }
        Handler().postDelayed(Runnable {
            context.startActivity(Intent(context, SettingActivity::class.java))
        }, 3000)
    }

}