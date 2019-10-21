package com.example.edz.ui;

import android.content.Intent;

import com.base.BaseActivity;
import com.example.edz.application.R;
import com.example.edz.pattern.adapter.Charger;
import com.example.edz.pattern.observer.IObserver;
import com.example.edz.pattern.observer.SubjectManager;
import com.mvp.IBasePresenter;
import com.utils.LogUtil;

import butterknife.OnClick;

/**
 * created by zhengweis on 2019/7/31.
 * description：
 */
public class MainActivity extends BaseActivity implements IObserver {

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
        SubjectManager.newInstance().attach(this);
    }

    @Override
    public IBasePresenter onBindPresenter() {
        return null;
    }

    @Override
    public void onEvent(Object object) {
        LogUtil.d(object.toString() + MainActivity.class.getSimpleName());
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        SubjectManager.newInstance().detach(this);
        startActivity(new Intent(this, EventActivity.class));

    }
}
