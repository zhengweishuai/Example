package com.example.edz.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.base.activity.BaseActivity;
import com.example.edz.application.R;

public class MainActivity extends BaseActivity {
    private Intent intent;

    @Override
    protected void initParms() {
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        findViewById(R.id.pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });
    }
}
