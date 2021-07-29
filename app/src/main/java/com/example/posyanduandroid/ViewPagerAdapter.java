package com.example.posyanduandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends PagerAdapter {
  Context context;

  // Array of images
  int[] images;

  // Layout Inflater
  LayoutInflater mLayoutInflater;

  @Override
  public int getCount() {
    return images.length;
  }

  @Override
  public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
    return view == ((LinearLayout) object);
  }
}
