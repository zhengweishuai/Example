package com.base.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.base.R;
import com.bumptech.glide.Glide;

import static android.content.Context.WINDOW_SERVICE;

/**
 * created by zhengweis on 2019/4/29.
 * description：
 */
public class FloatWindow implements View.OnClickListener {
    private Context context;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private int mY;
    //可移动的范围
    private int rangeTopY, rangeBottomY;
    private boolean isAdd = false;
    //悬浮窗是否在显示
    private boolean isShow;
    //悬浮窗是否展开
    private boolean isExpand = true;
    private ImageView mediaCover, playMedia, closeMedia, beforMedia, nextMedia;
    private RelativeLayout coverLayout;
    private RoundProgress progress;
    private LinearLayout mediaOption;
    private CloseMiniWindowListener closeMiniWindowListener;

    public FloatWindow(Context context) {
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
        mView = View.inflate(context, R.layout.layout_flow_window, null);
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        initViews();
        initTouchEvent();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        mediaCover = mView.findViewById(R.id.book_cover);
        mediaOption = mView.findViewById(R.id.media_option);
        coverLayout = mView.findViewById(R.id.cover_layout);
        progress = mView.findViewById(R.id.audio_progress);
        coverLayout.setOnClickListener(this);
        playMedia.setOnClickListener(this);
        closeMedia.setOnClickListener(this);
        beforMedia.setOnClickListener(this);
        nextMedia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cover_layout) {
            if (isExpand) {

            } else {
                updateViewVisible(true);
            }
        }
    }

    private void initTouchEvent() {
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int nowY = (int) event.getRawY();
                        int movedY = mY - nowY;
                        mY = nowY;
                        mLayoutParams.x = 30;
                        mLayoutParams.y = mLayoutParams.y + movedY;
                        if (mLayoutParams.y > rangeTopY) {
                            mLayoutParams.y = rangeTopY;
                            mY = rangeBottomY;
                        }
                        if (mLayoutParams.y < rangeBottomY) {
                            mLayoutParams.y = rangeBottomY;
                            mY = rangeTopY;
                        }
                        mWindowManager.updateViewLayout(mView, mLayoutParams);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    public interface CloseMiniWindowListener {
        void close();
    }

    public void setCloseListener(CloseMiniWindowListener closeMiniWindowListener) {
        this.closeMiniWindowListener = closeMiniWindowListener;
    }

    /**
     * 设置上下拖动的范围
     *
     * @param rangeTopY
     * @param rangeBottomY
     */
    public void setMoreRange(int rangeTopY, int rangeBottomY) {
        this.rangeTopY = rangeTopY;
        this.rangeBottomY = rangeBottomY;
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
        mLayoutParams.x = xOffset;
        mLayoutParams.y = yOffset;
        ViewTreeObserver topMenuViewTreeObserver = mView.getViewTreeObserver();
        topMenuViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int height = mView.getHeight();
                int[] position = new int[2];
                mView.getLocationOnScreen(position);
                mY = position[1] + height / 2;
                return true;
            }
        });
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


    public void updateViewVisible(boolean isExpand) {
        this.isExpand = isExpand;
        mediaOption.setVisibility(isExpand ? View.VISIBLE : View.GONE);
    }

    /**
     * 消失
     */
    public void dimiss() {
        if (isShow) {
            mView.setVisibility(View.INVISIBLE);
        }
        isShow = false;
    }

    /**
     * 清空
     */
    public void clear() {
        closeMiniWindowListener = null;
        mWindowManager.removeView(mView);
        mWindowManager = null;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void updateCoverImg(String url) {
        Glide.with(context).load(url).into(mediaCover);
    }
}
