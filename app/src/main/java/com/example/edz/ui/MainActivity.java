package com.example.edz.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.base.BaseActivity;
import com.example.edz.application.R;
import com.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by zhengweis on 2019/7/31.
 * description：
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParms() {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void doBusiness() {

    }

    @Override
    public IBasePresenter onBindPresenter() {
        return null;
    }
}
