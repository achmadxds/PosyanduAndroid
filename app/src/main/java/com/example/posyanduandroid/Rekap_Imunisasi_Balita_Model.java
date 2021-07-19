package com.example.posyanduandroid;

public class Rekap_Imunisasi_Balita_Model {
  private String nmImunisasi, tglImunisasi;

  public Rekap_Imunisasi_Balita_Model() {}

  public String getNmImunisasi() {
    return nmImunisasi;
  }

  public void setNmImunisasi(String nmImunisasi) {
    this.nmImunisasi = nmImunisasi;
  }

  public String getTglImunisasi() {
    return tglImunisasi;
  }

  public void setTglImunisasi(String tglImunisasi) {
    this.tglImunisasi = tglImunisasi;
  }

  public Rekap_Imunisasi_Balita_Model(String nmImunisasi, String tglImunisasi) {
    this.nmImunisasi = nmImunisasi;
    this.tglImunisasi = tglImunisasi;
  }
}
