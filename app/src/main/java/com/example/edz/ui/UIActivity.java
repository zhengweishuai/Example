package com.example.edz.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.base.activity.BaseActivity;
import com.base.utils.DensityUtil;
import com.example.edz.application.R;

public class UIActivity extends BaseActivity {
    Button optionBtn;
    Button optionBtnB;
    ViewStub viewStubImg;

    @Override
    protected int initLayout() {
        return R.layout.activity_ui;
    }

    @Override
    protected void initParms() {

    }

    @Override
    protected void initView() {
        optionBtn = findViewById(R.id.option);
        optionBtnB = findViewById(R.id.optionB);
    }

    @Override
    protected void initData() {
        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(UIActivity.this)) {
                        Toast.makeText(UIActivity.this, "当前无权限，请授权", Toast.LENGTH_SHORT);
                        startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
                    } else {
                        application.showFloatWindow();
                    }
                } else {
                    application.showFloatWindow();
                }
            }
        });
        optionBtnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.hideFloatWindow();
            }
        });
    }

}
