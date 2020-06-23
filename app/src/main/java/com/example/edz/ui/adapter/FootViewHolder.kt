package com.example.edz.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edz.R

/**
 * author : zhengweishuai
 * date : 2020/6/23 0023.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
class FootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var loadingProgress: ImageView = itemView.findViewById(R.id.pb_loading)
    var loadingMsg: TextView = itemView.findViewById(R.id.tv_loading)
}