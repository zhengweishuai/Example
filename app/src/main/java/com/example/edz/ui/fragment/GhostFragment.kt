package com.example.edz.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * author : zhengweishuai
 * date : 2020/7/7 0007.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class GhostFragment : Fragment() {
    private var requestCode = -1
    private var intent: Intent? = null
    private var callBack: ((Intent) -> Unit?)? = null


    fun init(requestCode: Int, intent: Intent, callBack: (result: Intent) -> Unit) {
        this.requestCode = requestCode
        this.intent = intent
        this.callBack = callBack
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack?.let { startActivityForResult(intent, requestCode) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK
                && requestCode == this.requestCode
                && data != null) {
            callBack?.let { it(data) }
        }
    }

    override fun onDetach() {
        super.onDetach()
        intent = null
        callBack = null
    }
}