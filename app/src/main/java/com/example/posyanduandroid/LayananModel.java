package com.example.posyanduandroid;

import android.app.Activity;

public class LayananModel {

  private String title;
  private String KodeJadwal;
  private int image;
  private Class activity;

  public LayananModel() {}

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

  public String getKodeJadwal() {
    return KodeJadwal;
  }

  public void setKodeJadwal(String kodeJadwal) {
    KodeJadwal = kodeJadwal;
  }

  public LayananModel(String title, int image, Class antrian, String kdJadwal) {
    this.title = title;
    this.image = image;
    this.activity = antrian;
    this.KodeJadwal = kdJadwal;
  }
}
