package com.base.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.base.utils.DensityUtil;

import static android.content.Context.WINDOW_SERVICE;

/**
 * created by zhengweis on 2019/4/29.
 * description：
 */
public class RongShuFloatWindow {
    private Context context;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private int mX, mY;
    private boolean isRemove = false;
    //可移动的范围
    private int rangeTopY, rangeBottonY;
    private ValueAnimator mAnimator;
    private boolean isAdd = false;
    private boolean isShow;

    public RongShuFloatWindow(Context context) {
        this.context = context;
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        mLayoutParams.format = PixelFormat.RGBA_8888;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mLayoutParams.width = 500;
        mLayoutParams.height = 100;
    }

    private void initTouchEvent() {
        mView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mX = (int) event.getRawX();
                        mY = (int) event.getRawY();
                        cancelAnimator();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int nowX = (int) event.getRawX();
                        int nowY = (int) event.getRawY();
                        int movedX = mX - nowX;
                        int movedY = mY - nowY;
                        mX = nowX;
                        mY = nowY;
                        mLayoutParams.x = mLayoutParams.x + movedX;
                        mLayoutParams.y = mLayoutParams.y + movedY;
                        if (mLayoutParams.y > rangeTopY) {
                            mLayoutParams.y = rangeTopY;
                            mWindowManager.updateViewLayout(mView, mLayoutParams);
                        } else {
                            mWindowManager.updateViewLayout(mView, mLayoutParams);
                        }
                        if (mLayoutParams.y < rangeBottonY) {
                            mLayoutParams.y = rangeBottonY;
                            mWindowManager.updateViewLayout(mView, mLayoutParams);
                        } else {
                            mWindowManager.updateViewLayout(mView, mLayoutParams);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mX = (int) event.getRawX();
                        mY = (int) event.getRawY();
                        int startX = (int) mView.getX();
                        int endX = (startX * 2 + v.getWidth() > DensityUtil.getScreenHeight(context) ?
                                DensityUtil.getScreenHeight(context) - v.getWidth() : 0);
                        mAnimator = ObjectAnimator.ofInt(startX, endX);
                        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int x = (int) animation.getAnimatedValue();
                                mLayoutParams.x = mX = x;
                                mWindowManager.updateViewLayout(mView, mLayoutParams);
                            }
                        });
                        startAnimator();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void startAnimator() {
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimator.removeAllUpdateListeners();
                mAnimator.removeAllListeners();
                mAnimator = null;

            }
        });
        mAnimator.start();
    }

    private void cancelAnimator() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
    }

    /**
     * 设置需要显示的view
     *
     * @param view
     */
    public void setView(View view) {
        this.mView = view;
        initTouchEvent();
    }

    /**
     * 设置上下拖动的范围
     *
     * @param rangeTopY
     * @param rangeBottonY
     */
    public void setMoreRange(int rangeTopY, int rangeBottonY) {
        this.rangeTopY = rangeTopY;
        this.rangeBottonY = rangeBottonY;
    }

    /**
     * 设置显示的初始位置
     *
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public void setGravity(int gravity, int xOffset, int yOffset) {
        mLayoutParams.gravity = gravity;
        mLayoutParams.x = mX = xOffset;
        mLayoutParams.y = mY = yOffset;
    }

    /**
     * 显示
     */
    public void show() {
        if (!isAdd) {
            mWindowManager.addView(mView, mLayoutParams);
            isAdd = true;
        } else if (!isShow) {
            mView.setVisibility(View.VISIBLE);
        }
        isShow = true;
    }

    /**
     * 消失
     */
    public void dimiss() {
        isRemove = true;
        if (isShow) {
            mView.setVisibility(View.INVISIBLE);
        }
        isShow = false;
//        mWindowManager.removeView(mView);
    }

    /**
     * 返回当前设置的view
     *
     * @return
     */
    public View getView() {
        return mView;
    }

    /**
     * 返回当前x轴坐标
     *
     * @return
     */
    public int getmX() {
        return mX;
    }

    /**
     * 返回当前y轴坐标
     *
     * @return
     */
    public int getmY() {
        return mY;
    }
}
