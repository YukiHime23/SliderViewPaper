package com.example.slider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class PagerAdapter extends android.support.v4.view.PagerAdapter {
    public PagerAdapter(Context context) {
        this.context = context;
    }

    Context context;
    LayoutInflater inflater;
    int[] list_image;
    Field[] id_image = R.drawable.class.getFields();

    public void getAllImage(){
        list_image = new int[id_image.length];
        for(int i = 0;i<id_image.length;i++){
            try {
                list_image[i] = id_image[i].getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
//    public int[] lst_image = {
//            R.drawable.ic_launcher_background,
//            R.drawable.ayanami,
//            R.drawable.chikuma,
//            R.drawable.laffey_bilibili,
//            R.drawable.abukuma_kai,
//            R.drawable.ayanami_kai,
//            R.drawable.ayanami_wedding,
//            R.drawable.crescent_kai,
//            R.drawable.curacoa_spring,
//            R.drawable.curlew_spring,
//            R.drawable.cygnet_christmas,
//            R.drawable.cygnet_kai,
//    };
//
//    public int[] lst_color = {
//            R.color.colorPrimary,
//            R.color.colorAccent,
//            R.color.colorPrimaryDark,
//            R.color.colorRed
//    };

//    public String[] lst_title = {
//            "ic_launcher_background",
//            "Ayanami",
//            "Chikuma",
//            "LaffeyBilibili","AbukumaKai","AyanamiKai"
//    };

    @Override
    public int getCount() {
        return id_image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pager,container,false);

        getAllImage();

        ImageView imageView = view.findViewById(R.id.slider);
        imageView.setImageResource(list_image[position]);

        TextView textView = view.findViewById(R.id.txt_title);
        textView.setText(id_image[position].getName());

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
