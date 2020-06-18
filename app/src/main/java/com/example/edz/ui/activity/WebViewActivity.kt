package com.example.edz.ui.activity

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.text.TextUtils
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.edz.R
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.databinding.ActivityWebViewBinding
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
    private val articleBean: ArticleListItemBean by lazy {
        intent.extras?.getSerializable("articleBean") as ArticleListItemBean
    }
    override fun attachLayoutRes(): Int = R.layout.activity_web_view

    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        tv_right.text = "收藏"
        showLoading(true)
        val url: String = articleBean.link
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
            finish()
        }
        rl_right.setOnClickListener {
            mViewModel.collectArticle(articleBean.id)
        }
    }

    override fun doBusiness() {

    }
}