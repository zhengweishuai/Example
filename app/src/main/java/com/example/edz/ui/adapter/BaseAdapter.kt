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
import com.example.edz.R

/**
 * author zhengweishuai
 * date 2020/5/26 0026.
 * description：
 */
abstract class BaseAdapter<T, DB : ViewDataBinding>(val mContext: Context, val br_id: Int) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mList = ArrayList<T>()

    // 普通布局
    private val TYPE_ITEM = 1

    // 脚布局
    private val TYPE_FOOTER = 2

    // 正在加载
    val LOADING = 1

    // 加载完成
    val LOADING_COMPLETE = 2

    // 加载到底
    val LOADING_END = 3

    // 空数据
    val LOADING_EMPTY = 4

    // 当前加载状态，默认为加载完成
    private var loadState = LOADING_EMPTY
    override fun getItemViewType(position: Int): Int {
        // 最后一个item设置为FooterView
        return if (position + 1 == itemCount) {
            TYPE_FOOTER
        } else {
            TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val view = LayoutInflater.from(mContext).inflate(ItemLayoutId(), parent, false)
            SupperViewHodel(view)
        } else {
            val view = LayoutInflater.from(mContext).inflate(R.layout.layout_load_footer, parent, false)
            FootViewHolder(view)
        }
    }

    override fun getItemCount(): Int = mList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SupperViewHodel) {
            val dataBinding: DB? = DataBindingUtil.getBinding<DB>(holder.itemView)
            dataBinding?.setVariable(br_id, mList[position])
            onBindVh(dataBinding, holder, position)
            dataBinding?.executePendingBindings()
        } else if (holder is FootViewHolder) {
            when (loadState) {
                LOADING -> {
                    holder.loadingProgress.visibility = View.VISIBLE
                    holder.loadingMsg.visibility = View.VISIBLE
                    holder.loadingMsg.text = "加载中..."
                    viewAnimator(holder.loadingProgress)
                }
                LOADING_COMPLETE -> {
                    holder.loadingProgress.visibility = View.INVISIBLE
                    holder.loadingMsg.visibility = View.VISIBLE
                    holder.loadingMsg.text = "加载完成"
                    viewAnimator(holder.loadingProgress)
                }
                LOADING_END -> {
                    holder.loadingProgress.visibility = View.GONE
                    holder.loadingMsg.visibility = View.VISIBLE
                    holder.loadingMsg.text = "--没有更多了--"
                    holder.loadingProgress.clearAnimation()
                }
                LOADING_EMPTY -> {
                    holder.loadingProgress.visibility = View.INVISIBLE
                    holder.loadingMsg.visibility = View.INVISIBLE
                    holder.loadingProgress.clearAnimation()
                }
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun viewAnimator(view: View){
        val rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        rotateAnimator.duration = 1000
        rotateAnimator.repeatMode = ValueAnimator.INFINITE
        rotateAnimator.repeatCount = ValueAnimator.INFINITE
        rotateAnimator.start()
    }

    abstract fun onBindVh(dataBinding: DB?, holder: SupperViewHodel, position: Int)

    abstract fun ItemLayoutId(): Int

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    open fun setLoadState(loadState: Int) {
        this.loadState = loadState
        notifyDataSetChanged()
    }

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