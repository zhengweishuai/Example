package com.example.edz.ui;

import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;

import com.base.activity.BaseActivity;
import com.example.edz.application.R;

import butterknife.BindView;
import butterknife.OnClick;

public class UIActivity extends BaseActivity {
    @BindView(R.id.option)
    Button optionBtn;
    @BindView(R.id.view_stub_img)
    ViewStub viewStubImg;
    private ImageView image;
    private View inflate;
    private boolean option = false;

    @Override
    protected int initLayout() {
        return R.layout.activity_ui;
    }

    @Override
    protected void initParms() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.option)
    public void onViewClicked() {
        option = !option;
        if (option) {
            showStubView();
        } else {
            hideStubView();
        }
    }

    private void showStubView() {
        if (inflate == null) {
            inflate = viewStubImg.inflate();
            image = inflate.findViewById(R.id.image);
        }
        image.setVisibility(View.VISIBLE);
    }

    private void hideStubView() {
        if (image != null) {
            image.setVisibility(View.GONE);
        }
    }
}
