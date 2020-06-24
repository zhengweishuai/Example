package com.example.edz.ui.adapter

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
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

    var mList = ArrayList<T>()

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

    @SuppressLint("WrongConstant")
    private fun viewAnimator(view: View) {
        val rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        rotateAnimator.duration = 1000
        rotateAnimator.repeatMode = ValueAnimator.INFINITE
        rotateAnimator.repeatCount = ValueAnimator.INFINITE
        rotateAnimator.start()
    }

    abstract fun onBindVh(dataBinding: DB?, holder: SupperViewHodel, position: Int)

    abstract fun ItemLayoutId(): Int

    fun setNewData(data: ArrayList<T>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(data: T) {
        mList.add(data)
        notifyItemInserted(mList.size)
    }

    fun addList(data: ArrayList<T>) {
        mList.addAll(data)
        if (mList.size == data.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeChanged(mList.size - data.size, data.size)
        }
    }

    fun removeItem(data: T) {
        val position = mList.indexOf(data)
        if (-1 == position) {
            return
        }
        mList.remove(data)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mList.size - position)
    }

    fun removeItem(pos: Int) {
        mList.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos, mList.size - pos)
    }
}