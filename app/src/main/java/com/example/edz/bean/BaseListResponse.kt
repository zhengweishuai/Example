package com.example.edz.bean

/**
 * author : zhengweishuai
 * date : 2020/6/24 0024.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
data class BaseListResponse<T>(var datas: ArrayList<T>?,
                               var curPage: Int,
                               var total: Int)