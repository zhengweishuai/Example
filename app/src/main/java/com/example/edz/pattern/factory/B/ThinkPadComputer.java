package com.example.edz.pattern.factory.B;

import com.utils.LogUtil;

/**
 * created by zhengweis on 2019/10/15.
 * description：
 */
public class ThinkPadComputer extends Computer {
    @Override
    public void palyGame() {
        LogUtil.d("thinkpad笔记本");
    }
}
