package com.example.edz.bean.response

/**
 * author : zhengweishuai
 * date : 2020/6/8 0008.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：注册接口返回实体类
 */
data class RegisterResponse(var id: String,
                            var nickname: String,
                            var publicName: String,
                            var email: String,
                            var icon: String,
                            var password: String)