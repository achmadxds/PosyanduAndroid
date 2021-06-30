package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Registrasi extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AlertDialog alertDialog = new AlertDialog.Builder(Registrasi.this).create(); //Read Update
    alertDialog.setTitle("PESAN!!");
    alertDialog.setMessage("Pastikan Anda Sudah Mengantongi Kode Unik Dari Poyandu!!");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.registrasi);
    alertDialog.show();
  }

  public void Kembali(View view) {
    Intent intent= new Intent(Registrasi.this, Login.class);
    startActivity(intent);
  }
}
