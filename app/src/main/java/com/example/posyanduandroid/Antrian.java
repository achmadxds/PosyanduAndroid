package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;

public class Antrian extends AppCompatActivity {

  Integer angka = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_antrian);
  }

  public void IncrementValue(View view) {
    angka++;

    TextView valueInc = (TextView) findViewById(R.id.valueIncrement);
    valueInc.setText(String.valueOf(angka));
  }
}