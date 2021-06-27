package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dashboard);
  }

  public void Beranda (View view){
    Intent intent= new Intent(Dashboard.this, Login.class);
    startActivity(intent);
  }
}
