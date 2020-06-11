package com.example.edz.ui.adapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * author zhengweishuai
 * date 2020/5/27 0027.
 * description：
 */
public class SupperViewHodel extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public ViewDataBinding getBinding() {
        return binding;
    }

    public SupperViewHodel(@NotNull View view) {
        super(view);
        binding = DataBindingUtil.bind(view);
    }
}
