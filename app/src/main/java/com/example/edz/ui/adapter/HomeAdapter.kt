package com.example.edz.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edz.R
import com.example.edz.bean.ArticleListItemBean
import com.example.edz.bean.BannerBean
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader


/**
 * author : zhengweishuai
 * date : 2020/8/17 0017.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
const val VIEWTYPE_BANNER = 1 //banner
const val VIEWTYPE_ARTICLE = 2 //文章

class HomeAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //banner数据
    var banners: MutableList<BannerBean> = mutableListOf()

    //文章列表数据
    var articles: MutableList<ArticleListItemBean> = mutableListOf()

    //点击监听
    private var listener: onItemClickListener? = null

    /*
    跟新banner
     */
    fun updateBanners(banners: MutableList<BannerBean>) {
        this.banners.addAll(banners)
        notifyDataSetChanged()
    }

    /*
    更新文章列表
     */
    fun updateArticles(articles: MutableList<ArticleListItemBean>) {
        this.articles.addAll(articles)
        notifyItemRangeChanged(this.articles.size - articles.size + 1
                , articles.size)
    }

    /*
    设置item点击回调
     */
    fun setOnItemClickListener(listener: onItemClickListener) {
        this.listener = listener
    }

    /**
     * 回调接口
     */
    interface onItemClickListener {
        //banner点击回调
        fun onBannerClickListener(position: Int)

        //文章列表点击回调
        fun onArticleClickListener(position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEWTYPE_BANNER;
        } else {
            VIEWTYPE_ARTICLE;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEWTYPE_BANNER) {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_home_banner, parent, false)
            BannnerViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_adapter_home_article, parent, false)
            ArticleViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (banners.isEmpty()) articles.size else articles.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BannnerViewHolder) {
            holder.banner.setImages(banners).setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context, any: Any, imageView: ImageView) {
                    Glide.with(context).load((any as BannerBean).imagePath).into(imageView)
                }
            })
            holder.banner.start()
            holder.banner.setOnBannerListener { bannerPositon ->
                listener?.let {
                    it.onBannerClickListener(bannerPositon)
                }
            }
        } else if (holder is ArticleViewHolder) {
            val newPositon = if (banners.size > 0) position - 1 else position
            holder.articleTitle.text = articles[newPositon].title
            holder.articleTag.text = articles[newPositon].superChapterName
            holder.articleAuthor.text = articles[newPositon].shareUser
            holder.articleTime.text = articles[newPositon].niceDate
            holder.itemView.setOnClickListener {
                listener?.let {
                    it.onArticleClickListener(position - 1)
                }
            }
        }
    }

    class BannnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val banner by lazy {
            itemView.findViewById<Banner>(R.id.cus_banner)
        }
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleTitle by lazy {
            itemView.findViewById<TextView>(R.id.article_title)
        }
        val articleTag by lazy {
            itemView.findViewById<TextView>(R.id.tv_tag)
        }
        val articleAuthor by lazy {
            itemView.findViewById<TextView>(R.id.tv_author)
        }
        val articleTime by lazy {
            itemView.findViewById<TextView>(R.id.tv_time)
        }
    }
}