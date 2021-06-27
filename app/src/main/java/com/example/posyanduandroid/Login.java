package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);

    Button btnLogin = (Button) findViewById(R.id.btnLogin);
    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Login.this, Dashboard.class);
        startActivity(i);
      }
    });

    Button btnToRegist = (Button) findViewById(R.id.btnToRegist);
    btnToRegist.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Login.this, Registrasi.class);
        startActivity(i);
      }
    });
  }
}
