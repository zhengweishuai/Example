package com.example.edz.ui;

import android.widget.EditText;

import com.base.BaseActivity;
import com.example.edz.application.R;
import com.example.edz.contract.LoginContract;
import com.example.edz.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginUi {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;

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
    public LoginPresenter onBindPresenter() {
        return new LoginPresenter(this);
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        getPresenter().login(name.getText().toString(), pwd.getText().toString());
    }

    @Override
    public void loginSuccess(String msg) {
        toast(msg);
    }

    @Override
    public void loginFaild(String msg) {
        toast(msg);
    }
}
