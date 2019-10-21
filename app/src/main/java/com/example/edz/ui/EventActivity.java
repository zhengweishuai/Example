package com.example.edz.ui;

import android.os.Bundle;
import android.widget.Button;

import com.base.BaseActivity;
import com.example.edz.application.R;
import com.example.edz.pattern.adapter.Charger;
import com.example.edz.pattern.adapter.ChargerAdapter;
import com.example.edz.pattern.adapter.IPhoneCharger;
import com.example.edz.pattern.factory.B.ComputerFactory;
import com.example.edz.pattern.observer.IObserver;
import com.example.edz.pattern.observer.SubjectManager;
import com.mvp.IBasePresenter;
import com.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by zhengweis on 2019/10/15.
 * description：
 */
public class EventActivity extends BaseActivity implements IObserver {
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int initLayout() {
        return R.layout.activity_event;
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
        LogUtil.d(object.toString() + EventActivity.class.getSimpleName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        ComputerFactory factory = new ComputerFactory();
        factory.createComputer(1).palyGame();
        factory.createComputer(2).palyGame();
        SubjectManager.newInstance().postObserver("测试--------------->");
        ChargerAdapter phoneCharger = new ChargerAdapter(new Charger());
        phoneCharger.voltage24();
    }

    //使用插座充电
    public void charge(){
    }
}
