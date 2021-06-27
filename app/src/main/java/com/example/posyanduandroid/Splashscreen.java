package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        final Intent intent = new Intent(Splashscreen.this, Login.class);
        startActivity(intent);
        finish();
      }
    }, 2000);
  }
}
