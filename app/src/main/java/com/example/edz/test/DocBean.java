package com.example.edz.test;

import com.utils.LogUtil;

/**
 * author : zhengweishuai
 * date : 2020/8/19 0019.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
public class DocBean implements Cloneable {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected DocBean clone() {
        try {
            DocBean bean = (DocBean) super.clone();
            return bean;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showContent() {
        LogUtil.d("-------------start-------------");
        LogUtil.d("\n" + name + "\n" + age + "age");
        LogUtil.d("-------------start-------------");
    }
}
