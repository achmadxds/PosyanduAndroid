package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Registrasi extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.registrasi);

    Button btnBackToLogin = (Button) findViewById(R.id.btnBackToLogin);
    btnBackToLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Registrasi.this, Login.class);
        startActivity(i);
      }
    });
  }

  public void Kembali(View view) {
    Intent intent= new Intent(Registrasi.this, Login.class);
    startActivity(intent);
  }
}
