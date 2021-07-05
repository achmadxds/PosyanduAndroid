package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RekapFisikActivity extends AppCompatActivity {

  @BindView(R.id.rv_jadwal)
  RecyclerView recyclerView;

  private RekapFisikAdapter jadwalAdapter;
  private ArrayList<RekapFisikModel> jadwalModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rekapfisikbalita);

    ButterKnife.bind(this);

    addData();
  }

  private void addData() {
    jadwalModel = new ArrayList<>();
    Date date = new Date();

    jadwalModel.add(new RekapFisikModel("12313123", "testing", date, "tempat"));
    jadwalModel.add(new RekapFisikModel("asd213", "123", date, "tempat"));
    jadwalModel.add(new RekapFisikModel("d123", "4asd", date, "tempat"));
    jadwalModel.add(new RekapFisikModel("dfas13", "xcvasd", date, "tempat"));

    jadwalAdapter = new RekapFisikAdapter(this, jadwalModel);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(jadwalAdapter);
  }
}