    package com.example.posyanduandroid;

    import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

    public class Beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
    }
        public void backtoMenu(View view){
            finish();
        }
}