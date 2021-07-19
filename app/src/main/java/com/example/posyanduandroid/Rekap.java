package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rekap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);
    }

    public void RekapImunisasiBalitaButton(View view) {
        Intent i = new Intent(Rekap.this, Rekap_Imunisasi_Balita.class);
        startActivity(i);
    }

    public void RekapFisikBalitaButton(View view) {
        Intent i = new Intent(Rekap.this, RekapFisikActivity.class);
        startActivity(i);
    }
}