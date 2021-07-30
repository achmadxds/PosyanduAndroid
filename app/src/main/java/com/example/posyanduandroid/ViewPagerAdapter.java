package com.example.posyanduandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
  Context context;

  // Array of images
  int[] images;

  // Layout Inflater
  LayoutInflater mLayoutInflater;

  public ViewPagerAdapter(Context context, int[] images) {
    this.context = context;
    this.images = images;
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return images.length;
  }

  @Override
  public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
    return view == ((LinearLayout) object);
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, final int position) {
    // inflating the item.xml
    View itemView = mLayoutInflater.inflate(R.layout.item_slider, container, false);

    // referencing the image view from the item.xml file
    ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

    // setting the image in the imageView
    imageView.setImageResource(images[position]);

    // Adding the View
    Objects.requireNonNull(container).addView(itemView);

    return itemView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((LinearLayout) object);
  }
}
