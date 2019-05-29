package com.example.edz.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.base.widget.toast.ToastUtils;
import com.example.edz.application.BR;

import java.io.Serializable;

/**
 * created by zhengweis on 2019/5/29.
 * description：
 */
public class User extends BaseObservable implements Serializable {

    private String name;
    private String age;

    public void msgOnClick(View view) {
        ToastUtils.show("click textview");
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
