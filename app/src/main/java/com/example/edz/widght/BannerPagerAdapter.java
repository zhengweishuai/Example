package com.example.edz.widght;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.edz.application.R;

import java.util.List;

/**
 * created by zhengweis on 2019/8/12.
 * description：
 */
public class BannerPagerAdapter extends PagerAdapter {
    private List<String> viewList;
    private Context context;

    public BannerPagerAdapter(Context context, List<String> viewList) {
        this.context = context;
        this.viewList = viewList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//当前滑动到的ViewPager页面
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
        ImageView imageView = view.findViewById(R.id.banner_item);
        Glide.with(context).load(viewList.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//每次划出当前页面的时候就销毁
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public int getCount() {//设置ViewPager有几个滑动页面
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方固定写法
    }
}
