package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;

public class Antrian extends AppCompatActivity {

  Integer angka = 1;
  private String MY_PREFS_NAME;
  private String TAG = "Antrian";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_antrian);
    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    int scoreViewA = prefs.getInt("scoreViewA", 0);
    int scoreViewB = prefs.getInt("scoreViewB", 0);

    Log.d(TAG, "onScore: " + scoreViewA);
    Log.d(TAG, "onScore: " + scoreViewB);
  }

  public void IncrementValue(View view) {
    angka++;

    TextView valueInc = (TextView) findViewById(R.id.valueIncrement);
    valueInc.setText(String.valueOf(angka));
  }
}