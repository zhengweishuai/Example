package com.example.edz.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.edz.application.R;
import com.example.edz.application.databinding.ActivityMainBinding;
import com.example.edz.bean.Song;
import com.example.edz.bean.User;

public class MainActivity extends Activity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.setName("梨花");
        user.setAge("28");
        Song song = new Song();
        song.title.set("你的背包");
        song.time.set("陈奕迅");
        binding.setMainData(user);
        binding.setSong(song);
    }
}
