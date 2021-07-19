package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

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

    TextView jamAntrian = findViewById(R.id.jamAntrian);
    TextView noUrut = findViewById(R.id.noUrutss);

    jamAntrian.setText(getJam.toString());
    noUrut.setText(getNoUrut.toString());
  }
}