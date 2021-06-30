package com.example.posyanduandroid;

import android.app.Activity;

public class DashboardModel {

  private String title;
  private int image;
  private Class activity;

  public DashboardModel() {}

  public DashboardModel(String title, int image, Class activity) {
    this.title = title;
    this.image = image;
    this.activity = activity;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getImage() {
    return image;
  }

  public void setImage(int image) {
    this.image = image;
  }

  public Class getActivity() {
    return activity;
  }

  public void setActivity(Class activity) {
    this.activity = activity;
  }
}
