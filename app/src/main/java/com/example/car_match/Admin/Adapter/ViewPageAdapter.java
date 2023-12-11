package com.example.car_match.Admin.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.car_match.R;

import java.util.ArrayList;

public class ViewPageAdapter extends PagerAdapter {
    private Context context;
    ArrayList<Uri> ImageUrls;
    LayoutInflater layoutInflater;

    public ViewPageAdapter(Context context, ArrayList<Uri> imageUri) {
        this.context = context;
        ImageUrls = imageUri;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ImageUrls.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=layoutInflater.inflate(R.layout.show_image_seller,container,false);
        ImageView imageView=view.findViewById(R.id.UploadImage);
        imageView.setImageURI(ImageUrls.get(position));
        container.addView(view);
        return  view;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ((RelativeLayout)object).removeView(container);
    }
}
