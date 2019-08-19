package com.example.edz.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.Scroller;

import com.base.BaseActivity;
import com.example.edz.application.R;
import com.example.edz.contract.LoginContract;
import com.example.edz.presenter.LoginPresenter;
import com.example.edz.widght.BannerPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginUi {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<String> list;
    private int currentIndex = 0;
    private Handler handler = new Handler();

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initParms() {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        setViewPagerScroller();
        list = new ArrayList<>();
        list.add("http://img.52rongshu.com:10014//UploadImgFiles//20190730//21bc13b0-abaa-4463-8634-cf73027bb6d1.jpg");
        list.add("http://img.52rongshu.com:10014//UploadImgFiles//20190730//3f9ef730-467a-4763-9dd0-fb9669f87149.jpg");
        list.add("http://img.52rongshu.com:10014//UploadImgFiles//20190715//8673af94-7aef-405c-8010-b58c1ae76dcc.jpg");
        list.add("http://img.52rongshu.com:10014//UploadImgFiles//20190730//28472c81-213d-4d30-80c5-fd46a6784360.jpg");
        list.add("http://img.52rongshu.com:10014//UploadImgFiles//20190701//c518c99d-c6df-49ea-b7fc-1bf414dd138b.jpg");
        list.add("http://img.52rongshu.com:10014//UploadImgFiles//20190725//64417f8a-8d4e-428a-b4bf-8365a78ce6ad.jpg");
        BannerPagerAdapter adapter = new BannerPagerAdapter(this, list);
        vp.setAdapter(adapter);
        vp.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handler.removeCallbacks(runnable);
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    handler.postDelayed(runnable, 2000);
                    break;
            }
            return false;
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        handler.postDelayed(runnable, 2000);
    }

    /**
     * 展开/折叠 mini窗
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentIndex++;
            if (currentIndex >= list.size()) {
                currentIndex = 0;
            }
            vp.setCurrentItem(currentIndex, true);
            handler.postDelayed(runnable, 2000);
        }
    };

    private void setViewPagerScroller() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            Scroller scroller = new Scroller(this, (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, duration * 5);    // 这里是关键，将duration变长或变短
                }
            };
            scrollerField.set(vp, scroller);
        } catch (NoSuchFieldException e) {
            // Do nothing.
        } catch (IllegalAccessException e) {
            // Do nothing.
        }
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
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginFaild(String msg) {
        toast(msg);
    }

}
