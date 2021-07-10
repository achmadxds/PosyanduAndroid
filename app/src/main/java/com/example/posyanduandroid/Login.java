package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;

public class Login extends AppCompatActivity {
  private EditText username, password;
  private Button loginButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);

    username = findViewById(R.id.uName);
    password = findViewById(R.id.passwords);
    loginButton = findViewById(R.id.btnLogin);

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String usm = username.getText().toString().trim();
        String pswd = password.getText().toString().trim();

        if(usm.isEmpty() && pswd.isEmpty()) {
          Toast.makeText(getApplicationContext(),"Mohon isi hadulu!",Toast.LENGTH_SHORT).show();
        }

        if(!usm.isEmpty() && !pswd.isEmpty()) {
          Intent i = new Intent(Login.this, Dashboard.class);
          startActivity(i);
          finish();
        }
//        Toast.makeText(getApplicationContext(),usm,Toast.LENGTH_SHORT).show();
      }
    });
  }

  public void MengLogin(View view) {
    Intent i = new Intent(Login.this, Dashboard.class);
    startActivity(i);
    finish();
  }

  public void RegistrasiView(View view) {
    Intent i = new Intent(Login.this, Registrasi.class);
    startActivity(i);

  }
}
