package com.example.edz.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Build
import android.text.TextUtils
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.constant.AppStaticRes
import com.example.edz.R
import com.example.edz.databinding.ActivityWebViewBinding
import com.example.edz.utils.finish
import com.example.edz.viewmodel.WebViewModel
import com.mvvm.BaseMvvmActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.layout_base_title.*

/**
 * author : zhengweishuai
 * date : 2020/6/11 0011.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class WebViewActivity : BaseMvvmActivity<WebViewModel, ActivityWebViewBinding>() {
    private var url: String = ""
    private var isCollect = false
    private var originId: Int = 0
    private var id: Int = 0


    /**
     * url 链接
     * id 当前id
     * originId 可不传
     * isCollect 收藏状态
     */
    companion object {
        fun start(context: Context, url: String, id: Int, originId: Int, isCollect: Boolean) {
            val starter = Intent(context, WebViewActivity::class.java)
            starter.putExtra("web_url", url)
            starter.putExtra("id", id)
            starter.putExtra("originId", originId)
            starter.putExtra("is_collect", isCollect)
            if (context is AppCompatActivity) {
                context.startActivityForResult(starter, AppStaticRes.ARTICLE_DETAIL_CODE)
            }
        }

        fun start(context: Fragment, url: String, id: Int, originId: Int, isCollect: Boolean) {
            val starter = Intent(context.requireContext(), WebViewActivity::class.java)
            starter.putExtra("web_url", url)
            starter.putExtra("id", id)
            starter.putExtra("originId", originId)
            starter.putExtra("is_collect", isCollect)
            context.startActivityForResult(starter, AppStaticRes.ARTICLE_DETAIL_CODE)
        }
    }

    override fun attachLayoutRes(): Int = R.layout.activity_web_view

    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        url = intent.extras?.getString("web_url").toString()
        id = intent.extras?.getInt("id")!!
        originId = intent.extras?.getInt("originId")!!
        isCollect = intent.extras?.getBoolean("is_collect")!!
        iv_right.setBackgroundResource(if (isCollect) R.drawable.collect_select else R.drawable.collect_default)
        showLoading(true)
        val settings = wb.settings
        settings.javaScriptEnabled = true//设置WebView属性，能够执行Javascript脚本
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.allowFileAccess = true //设置可以访问文件
        settings.builtInZoomControls = false //设置支持缩放
        settings.setSupportZoom(true)
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        settings.userAgentString = "sxyy, android"
        wb.loadUrl(url)
    }

    override fun doListener() {
        wb.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                super.onReceivedSslError(view, handler, error)
                handler.proceed()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                showLoading(false)
                if (!TextUtils.isEmpty(view.title)) {
                    middle_title.text = view.title
                }
            }
        }

        rl_left.setOnClickListener {
            onBackPressed()
        }
        rl_right.setOnClickListener {
            if (isCollect) {
                mViewModel.unCollectArticle(id, originId)
            } else {
                mViewModel.collectArticle(id)
            }
        }
    }

    override fun doBusiness() {
        mViewModel.isollect.observe(this, Observer {
            isCollect = it
            iv_right.setBackgroundResource(if (isCollect) R.drawable.collect_select else R.drawable.collect_default)
        })
    }

    override fun onBackPressed() {
        finish("is_collect" to isCollect)
//        val intent = Intent()
//        intent.putExtra("is_collect", isCollect)
//        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}