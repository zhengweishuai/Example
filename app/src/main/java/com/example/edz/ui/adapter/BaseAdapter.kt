package com.example.edz.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * author zhengweishuai
 * date 2020/5/26 0026.
 * description：
 */
abstract class BaseAdapter<T, DB : ViewDataBinding>(val mContext: Context, val br_id: Int) :
        RecyclerView.Adapter<SupperViewHodel>() {

    var mList = mutableListOf<T>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupperViewHodel {
        val view = LayoutInflater.from(mContext).inflate(ItemLayoutId(), parent, false)
        return SupperViewHodel(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: SupperViewHodel, position: Int) {
        val dataBinding: DB? = DataBindingUtil.getBinding<DB>(holder.itemView)
        dataBinding?.setVariable(br_id, mList[position])
        onBindVh(dataBinding, holder, position)
        dataBinding?.executePendingBindings()
    }

    abstract fun onBindVh(dataBinding: DB?, holder: SupperViewHodel, position: Int)

    abstract fun ItemLayoutId(): Int

    fun setNewData(data: Collection<T>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(data: T) {
        mList.add(data)
        notifyItemInserted(mList.size)
    }

    fun addList(data: Collection<T>) {
        mList.addAll(data)
        if (mList.size == data.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeChanged(mList.size - data.size, data.size)
        }
    }

    fun removeItem(data: T) {
        mList.remove(data)
        val position = mList.indexOf(data)
        if (-1 == position) {
            return
        }
        notifyItemRemoved(position)
    }

    fun removeItem(pos: Int) {
        mList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}