package com.example.edz.bean

import java.io.Serializable

/**
 * author : zhengweishuai
 * date : 2020/6/11 0011.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
data class ArticleListItemBean(var id: Int?,
                               var title: String,
                               var link: String,
                               var niceDate: String) : Serializable