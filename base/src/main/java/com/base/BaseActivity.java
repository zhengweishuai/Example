package com.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.mvp.IBasePresenter;
import com.mvp.IBaseView;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.utils.DensityUtil;
import com.widget.loading.SlackLoadingView;
import com.widget.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePresenter> extends RxAppCompatActivity implements IBaseView {
    private int activityCloseEnterAnimation;
    private int activityCloseExitAnimation;
    private int activityOpenEnterAnimation;
    private int activityOpenExitAnimation;
    private FragmentManager manager;
    //当前Activity渲染的视图View
    protected View mContextView = null;
    protected RelativeLayout rootLayout;
    protected View loadingView;
    private Unbinder bind;
    private P presenter;
    private int loadingCount = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        obtainTheme();
        super.onCreate(savedInstanceState);
        manager = getSupportFragmentManager();
        //初始化沉浸式状态栏
        initTitleBar();
        mContextView = LayoutInflater.from(this).inflate(initLayout(), null);
        setContentView(generateContentView(mContextView));
        //绑定控件
        bind = ButterKnife.bind(this);
        initParms();
        initView();
        doBusiness();
    }

    /**
     * 绑定布局
     */
    @LayoutRes
    protected abstract int initLayout();

    /**
     * 初始化 参数
     */
    protected abstract void initParms();

    /**
     * 初始话 view
     */
    protected abstract void initView();

    /**
     * 处理业务
     */
    protected abstract void doBusiness();

    /**
     * 创建 Presenter
     */
    public abstract P onBindPresenter();


    /**
     * 获取 Presenter 对象，在需要获取时才创建Presenter，起到懒加载作用
     */
    public P getPresenter() {
        if (presenter == null) {
            presenter = onBindPresenter();
        }
        return presenter;
    }

    @Override
    public Activity getSelfActivity() {
        return this;
    }

    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        if (presenter != null) {
            presenter.detachView();
        }
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }

    /**
     * 生成根布局view
     */
    protected View generateContentView(View view) {
        rootLayout = new RelativeLayout(getBaseContext());
        rootLayout.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int top = DensityUtil.getStateBar(this);
        rootLayout.setPadding(0, top, 0, 0);
        return rootLayout;
    }

    /**
     * 隐藏状态栏
     */
    protected void hintTitleBar() {
        rootLayout.setPadding(0, 0, 0, 0);
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initTitleBar() {
        ImmersionBar immersionBar = ImmersionBar.with(this);
        if (ImmersionBar.isSupportStatusBarDarkFont()) {//是否支持修改状态栏字体颜色
            immersionBar.statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                    .flymeOSStatusBarFontColor(R.color.color_2c2c2c)  //修改flyme OS状态栏字体颜色
                    .init();  //必须调用方可沉浸式
        } else {
            immersionBar.flymeOSStatusBarFontColor(R.color.color_2c2c2c)  //修改flyme OS状态栏字体颜色
                    //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                    .statusBarDarkFont(true, 1.0f)
                    .init();  //必须调用方可沉浸式
        }
    }

    private void obtainTheme() {
        try {
            if (getTheme() != null) {
                TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
                int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
                activityStyle.recycle();

                TypedArray animStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId,
                        new int[]{android.R.attr.activityOpenEnterAnimation, android.R.attr.activityCloseExitAnimation});

                activityOpenEnterAnimation = animStyle.getResourceId(0, 0);
                activityCloseExitAnimation = animStyle.getResourceId(1, 0);
                animStyle.recycle();

                TypedArray anim = getTheme().obtainStyledAttributes(windowAnimationStyleResId,
                        new int[]{android.R.attr.activityOpenExitAnimation, android.R.attr.activityCloseEnterAnimation});

                activityOpenExitAnimation = animStyle.getResourceId(0, 0);
                activityCloseEnterAnimation = animStyle.getResourceId(1, 0);

                anim.recycle();
            }
        } catch (Exception e) {

        }
        overridePendingTransition(activityOpenEnterAnimation, activityOpenExitAnimation);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }

    public void toast(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 非必加
        // 如果你的app可以横竖屏切换，适配了4.4或者华为emui3.1系统手机，并且navigationBarWithKitkatEnable为true，
        // 请务必在onConfigurationChanged方法里添加如下代码，同时满足这三个条件才需要加上代码哦：
        // 1、横竖屏可以切换；
        // 2、android4.4或者华为emui3.1系统手机；
        // 3、navigationBarWithKitkatEnable为true
        // 否则请忽略
        ImmersionBar.with(this).init();
    }

    /**
     * 替换fragment
     *
     * @param cls         fragment的类名
     * @param tag         fragment的tag
     * @param containerId fragment的containerID
     * @param bundle      要传递的数据
     * @param listener    回调监听函数
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseFragment> T replaceFragment(Class<T> cls, String tag, int containerId, Bundle bundle, FragmentListener listener) {
        if (this.isFinishing()) {
            return null;
        }
        BaseFragment fragment = null;
        FragmentTransaction ft = manager.beginTransaction();

        try {
            fragment = cls.newInstance();
            fragment.setArguments(bundle);
            fragment.setCallback(listener);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (fragment == null) {
            toast("fragment is null");
            return null;
        }

        ft.replace(containerId, fragment, tag);
        ft.commitAllowingStateLoss();
        return (T) fragment;
    }

    /**
     * 开启，关闭软键盘
     */
    public void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (null == imm)
            return;

        if (isShow) {
            if (getCurrentFocus() != null) {
                //有焦点打开
                imm.showSoftInput(getCurrentFocus(), 0);
            } else {
                //无焦点打开
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                //有焦点关闭
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                //无焦点关闭
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }
    }

    /**
     * 显示loading动画
     *
     * @param show the show
     */
    public void showLoading(boolean show) {
        if (!isFinishing()) {
            if (show) {
                if (loadingView == null) {
                    loadingView = LayoutInflater.from(this).inflate(R.layout.loading_layout, null);
                }
                if (loadingCount == 0) {
                    rootLayout.addView(loadingView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    onLoadingViewAdded(loadingView);
                }
                loadingCount++;
            } else {
                loadingCount--;
                if (loadingCount <= 0) {
                    if (loadingView != null) {
                        rootLayout.removeView(loadingView);
                        onLoadingViewRemoved(loadingView);
                    }
                    loadingCount = 0;
                }
            }
        }
    }

    protected void onLoadingViewRemoved(View loadingView) {
        SlackLoadingView view = loadingView.findViewById(R.id.animation_view);
        if (view != null) {

            view.start();
        }
    }

    protected void onLoadingViewAdded(View loadingView) {
        SlackLoadingView view = loadingView.findViewById(R.id.animation_view);
        if (view != null) {
            view.start();
        }
    }

    /**
     * 是否正在显示loading
     */
    public boolean isShowLoading() {
        if (loadingCount > 0) {
            return true;
        } else {
            return false;
        }
    }
}
