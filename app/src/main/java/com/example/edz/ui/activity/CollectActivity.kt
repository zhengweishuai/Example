package com.example.edz.ui.activity

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edz.BR
import com.example.edz.R
import com.example.edz.databinding.ActivityCollectBinding
import com.example.edz.ui.adapter.DiscoverArticleAdapter
import com.example.edz.utils.ItemTouchHelperCallback
import com.example.edz.viewmodel.CollectViewModel
import com.mvvm.BaseMvvmActivity
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.layout_base_title.*


/**
 * author : zhengweishuai
 * date : 2020/6/18 0018.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class CollectActivity : BaseMvvmActivity<CollectViewModel, ActivityCollectBinding>() {
    override fun attachLayoutRes(): Int = R.layout.activity_collect

    override fun initViews() {
        middle_title.text = "我的收藏"
        mDataBind.articleManager = LinearLayoutManager(this)
        mDataBind.articleAdapter = DiscoverArticleAdapter(this, BR.data, true).apply {
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
}