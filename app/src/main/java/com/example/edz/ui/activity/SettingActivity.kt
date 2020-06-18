package com.example.edz.ui.activity

import android.content.Intent
import com.example.edz.R
import com.example.edz.databinding.ActivitySettingBinding
import com.constant.SPConfigs
import com.example.edz.ui.widget.CustomPopup
import com.example.edz.viewmodel.SettingViewModel
import com.lxj.xpopup.XPopup
import com.mvvm.BaseMvvmActivity
import com.utils.SPUtils
import kotlinx.android.synthetic.main.layout_base_title.*

/**
 * author : zhengweishuai
 * date : 2020/6/16 0016.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class SettingActivity : BaseMvvmActivity<SettingViewModel, ActivitySettingBinding>() {
    override fun attachLayoutRes(): Int = R.layout.activity_setting

    override fun initViews() {
        mDataBind.click = ProxyClick()
        middle_title.text = "设置"
        rl_left.setOnClickListener { finish() }
    }

    override fun doListener() {
    }

    override fun doBusiness() {
    }

    inner class ProxyClick() {
        fun logout() {
            XPopup.Builder(this@SettingActivity)
                    .asCustom(CustomPopup(this@SettingActivity, leftClickListener = {
                        it.dismiss()
                    }, rightClickListener = {
                        SPUtils.removePreferences(SPConfigs.USER_DATA)
                        it.dismiss()
                        finish()
                        startActivity(Intent(this@SettingActivity, MainActivity::class.java))
                    }, content = "是否退出登录"))
                    .show()
        }
    }

}