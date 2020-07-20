package com.example.edz.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.constant.AppStaticRes
import com.example.edz.BR
import com.example.edz.R
import com.example.edz.databinding.ActivityCollectBinding
import com.example.edz.ui.adapter.DiscoverArticleAdapter
import com.example.edz.ui.widget.CustomPopup
import com.example.edz.utils.toActivity
import com.example.edz.viewmodel.CollectViewModel
import com.lxj.xpopup.XPopup
import com.mvvm.BaseMvvmActivity
import kotlinx.android.synthetic.main.layout_base_title.*


/**
 * author : zhengweishuai
 * date : 2020/6/18 0018.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class CollectActivity : BaseMvvmActivity<CollectViewModel, ActivityCollectBinding>() {
    private var mPosition: Int = -1
    override fun attachLayoutRes(): Int = R.layout.activity_collect

    override fun initViews() {
        middle_title.text = "我的收藏"
        XPopup.Builder(this)
                .asCustom(CustomPopup(this, leftClickListener = {

                }, rightClickListener = {

                }, content = "列表条目左滑可以删除收藏", left = "", right = "确定"))
                .show()
        mDataBind.articleManager = LinearLayoutManager(this)
        mDataBind.articleAdapter = DiscoverArticleAdapter(this,
                BR.data,
                true,
                itemClick = {
                    mPosition = it
                    toActivity<WebViewActivity>(
                            "web_url" to mDataBind.articleAdapter!!.mList[it].link,
                            "id" to mDataBind.articleAdapter!!.mList[it].link,
                            "originId" to mDataBind.articleAdapter!!.mList[it].id,
                            "is_collect" to true)
//                    WebViewActivity.start(this,
//                            mDataBind.articleAdapter!!.mList[it].link,
//                            mDataBind.articleAdapter!!.mList[it].id,
//                            mDataBind.articleAdapter!!.mList[it].originId,
//                            true)
                }).apply {
            mViewModel.articles.observe(this@CollectActivity, Observer {
                addList(it)
            })
            this.deletePos.observe(this@CollectActivity, Observer {
                mViewModel.cancleCollectArticle(mList[it].id, mList[it].originId)
                removeItem(it)
            })
        }
    }

    override fun doListener() {
        rl_left.setOnClickListener { finish() }
    }

    override fun doBusiness() {
        mViewModel.requsetCollectArticle(0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppStaticRes.ARTICLE_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val isCollect = intent.getBooleanExtra("is_collect", false)
                if (!isCollect && mPosition != -1) {
                    mDataBind.articleAdapter?.let {
                        it.removeItem(mPosition)
                    }
                }
            }
        }
    }
}