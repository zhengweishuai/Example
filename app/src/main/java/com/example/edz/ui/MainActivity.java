package com.example.edz.ui;

import android.widget.TextView;

import com.base.BaseActivity;
import com.example.edz.application.R;
import com.http.ApiHelper;
import com.http.ApiCallBack;
import com.mvp.IBasePresenter;

import butterknife.BindView;

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
        ApiHelper.getRecommendAuthors(this, new ApiCallBack<Object>(this) {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }

            @Override
            public boolean isShowLoading() {
                return false;
            }
        });
    }

    @Override
    public IBasePresenter onBindPresenter() {
        return null;
    }

}
