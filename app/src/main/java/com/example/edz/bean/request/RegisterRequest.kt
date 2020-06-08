package com.example.edz.bean.request

/**
 * author : zhengweishuai
 * date : 2020/6/8 0008.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：注册请求体
 * 用户名 username
 * 密码 password
 * 重复密码 repassword
 */
data class RegisterRequest(var username: String, var password: String, var repassword: String)