package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class After_Antrian extends AppCompatActivity {
  SharedPreferences mPrefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_after_antrian);
    ShowData();
  }

  public void ShowData() {
    mPrefs = getSharedPreferences("MyPrefs",0);
    String getJam = mPrefs.getString("jamAntrian", "");
    String getNoUrut = mPrefs.getString("noUruts", "");

    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    TextView jamAntrian = findViewById(R.id.jamAntrian);
    TextView noUrut = findViewById(R.id.noUrutss);

    noUrut.setText(getNoUrut.toString());

    try {
      Date date = format.parse(getJam);
      SimpleDateFormat print = new SimpleDateFormat("HH:mm");

      jamAntrian.setText(print.format(date));

    } catch (ParseException e) {
      e.printStackTrace();
    }


  }

  public void Backed(View view) {
    Intent i = new Intent(After_Antrian.this, Layanan.class);
    startActivity(i);
    finish();
  }
}