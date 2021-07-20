package com.example.posyanduandroid;

public class KonsultasiModel {
    private int idAnggota;
    private String idAdmin, isi, balasan, status, tgl_masuk, tgl_balas;

    public KonsultasiModel() {}

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getBalasan() {
        return balasan;
    }

    public void setBalasan(String balasan) {
        this.balasan = balasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }

    public String getTgl_balas() {
        return tgl_balas;
    }

    public void setTgl_balas(String tgl_balas) {
        this.tgl_balas = tgl_balas;
    }
}
