package com.example.edz.bean

import java.io.Serializable

/**
 * author : zhengweishuai
 * date : 2020/6/17 0017.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class ProjectDetailBean(val id: Int,
                        val author: String = "",
                        val desc: String = "",
                        val envelopePic: String = "",
                        val link: String = "",
                        var collect: Boolean,
                        val niceDate: String = "",
                        val projectLink: String = "") : Serializable