package com.network

/**
 * author : zhengweishuai
 * date : 2020/6/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
data class INetResponse<T>(var errorCode: Int,
                           var errorMsg: String,
                           var data: T)