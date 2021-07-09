package com.example.posyanduandroid;

import java.util.Date;

public class RekapFisikModel {

  private String nama, berat, panjang, tanggal;

  public RekapFisikModel(){}

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getBerat() {
    return berat;
  }

  public void setBerat(String berat) {
    this.berat = berat;
  }

  public String getPanjang() {
    return panjang;
  }

  public void setPanjang(String panjang) {
    this.panjang = panjang;
  }

  public String getTanggal() {
    return tanggal;
  }

  public void setTanggal(String tanggal) {
    this.tanggal = tanggal;
  }

  public RekapFisikModel(String nama, String berat, String tanggal, String panjang) {
    this.nama = nama;
    this.berat = berat;
    this.tanggal = tanggal;
    this.panjang = panjang;
  }
}
