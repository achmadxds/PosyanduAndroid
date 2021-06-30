package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JadwalActivity extends AppCompatActivity {

  @BindView(R.id.rv_jadwal)
  RecyclerView recyclerView;

  private JadwalAdapter jadwalAdapter;
  private ArrayList<JadwalModel> jadwalModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_jadwal);

    ButterKnife.bind(this);

    addData();
  }

  private void addData() {
    jadwalModel = new ArrayList<>();
    Date date = new Date();

    jadwalModel.add(new JadwalModel("12313123", "testing", date, "tempat"));
    jadwalModel.add(new JadwalModel("asd213", "123", date, "tempat"));
    jadwalModel.add(new JadwalModel("d123", "4asd", date, "tempat"));
    jadwalModel.add(new JadwalModel("dfas13", "xcvasd", date, "tempat"));

    jadwalAdapter = new JadwalAdapter(this, jadwalModel);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(jadwalAdapter);
  }
}