package com.example.posyanduandroid;

import android.app.Activity;

public class LayananModel {

  private String title;
  private String KodeJadwal;
  private String parentAntrian;
  private String idAntrian;
  private String jamParent;
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

  public String getParentAntrian() {
    return parentAntrian;
  }

  public void setParentAntrian(String parentAntrian) {
    this.parentAntrian = parentAntrian;
  }

  public String getIdAntrian() {
    return idAntrian;
  }

  public void setIdAntrian(String idAntrian) {
    this.idAntrian = idAntrian;
  }

  public String getJamParent() {
    return jamParent;
  }

  public void setJamParent(String jamParent) {
    this.jamParent = jamParent;
  }

  public LayananModel(String title, int image, Class antrian, String kdJadwal, String parentAntrian, String idAntrian, String jamParent) {
    this.title = title;
    this.image = image;
    this.activity = antrian;
    this.KodeJadwal = kdJadwal;
    this.parentAntrian = parentAntrian;
    this.idAntrian = idAntrian;
    this.jamParent = jamParent;
  }
}
