package com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.RelativeLayout;

import com.utils.DensityUtil;

/**
 * created by zhengweis on 2019/5/6.
 * description：
 */
public class MLinearlayout extends RelativeLayout {
    private int yyy = -1;
    private int xxx = -1;
    private boolean isMove = false;

    public MLinearlayout(Context context) {
        super(context);
    }

    public MLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {


            case MotionEvent.ACTION_DOWN:
                isMove = false;
                //此处为break所以返回值为false执行 子OnClick 所以父onTouch中没有Down 所以子view中初始化也要在move的第一个 坐标中获取
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (!isMove) {
                    return false;
                }
                isMove = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isMove) {
                    yyy = (int) event.getRawY();
                    xxx = (int) event.getRawX();
                }
                isMove = true;
                //细节优化 短距离移除
                float moveY = event.getRawY();
                float moveX = event.getRawX();
                //如果是非点击事件就拦截 让父布局接手onTouch 否则执行子ViewOnClick
                if (Math.abs(moveY - yyy) > DensityUtil.dip2px(getContext(), 20)
                        || Math.abs(moveX - xxx) > DensityUtil.dip2px(getContext(), 20)) {
                    final ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                    return true;
                }
//                if (Math.abs(moveY - yyy) < Math.abs(moveX - xxx)) {
//                    Log.i("rex", "onInterceptTouchEvent --- 横滑");
////                    return false;
//                }
                break;

        }
        return false;
    }
}
