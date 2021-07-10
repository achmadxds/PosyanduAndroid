package com.example.posyanduandroid;

public class LayananModel {

  private String title;
  private Class activity;

  public LayananModel() {}

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Class getActivity() {
    return activity;
  }

  public void setActivity(Class activity) {
    this.activity = activity;
  }

  public LayananModel(String title, Class activity) {
    this.title = title;
    this.activity = activity;
  }
}
