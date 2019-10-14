package com.example.edz.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.base.BaseActivity;
import com.example.edz.application.R;
import com.example.edz.contract.LoginContract;
import com.example.edz.presenter.LoginPresenter;
import com.example.edz.widght.BigImageView;
import com.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginUi {

    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_3)
    BigImageView img3;
    @BindView(R.id.main_layout)
    ScrollView mainLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initParms() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void doBusiness() {

        try {
            InputStream inputStream = getAssets().open("qmsht.jpg");
            img2.setImageBitmap(BitmapFactory.decodeStream(inputStream));

            //设置显示区域
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options mOptions = new BitmapFactory.Options();
            mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            img1.post(new Runnable() {
                @Override
                public void run() {
                    int width = img1.getWidth();
                    int height = img1.getHeight();
                    LogUtil.d("----------" + width + ",  " + height);
                    Bitmap bitmap = decoder.decodeRegion(new Rect(0, 0, width, height), mOptions);
                    img1.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public LoginPresenter onBindPresenter() {
        return null;
    }


    @Override
    public void loginSuccess(String msg) {

    }

    @Override
    public void loginFaild(String msg) {

    }
}
