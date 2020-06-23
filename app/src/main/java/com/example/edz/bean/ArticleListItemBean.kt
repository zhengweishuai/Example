package com.example.edz.bean

import java.io.Serializable

/**
 * author : zhengweishuai
 * date : 2020/6/11 0011.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
data class ArticleListItemBean(var id: Int,
                               var originId: Int = -1,
                               var title: String,
                               var link: String,
                               var collect: Boolean,
                               var niceDate: String) : Serializable