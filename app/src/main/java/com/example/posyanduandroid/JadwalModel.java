package com.example.posyanduandroid;

import java.util.Date;

public class JadwalModel {

  private String kode, program, tempat;
  private Date tanggal;

  public JadwalModel(){}

  public JadwalModel(String kode, String program, Date tanggal, String tempat) {
    this.kode = kode;
    this.program = program;
    this.tanggal = tanggal;
    this.tempat = tempat;
  }

  public String getKode() {
    return kode;
  }

  public void setKode(String kode) {
    this.kode = kode;
  }

  public String getProgram() {
    return program;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  public String getTempat() {
    return tempat;
  }

  public void setTempat(String tempat) {
    this.tempat = tempat;
  }

  public Date getTanggal() {
    return tanggal;
  }

  public void setTanggal(Date tanggal) {
    this.tanggal = tanggal;
  }
}
