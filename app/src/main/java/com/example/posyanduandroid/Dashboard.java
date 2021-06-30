package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dashboard extends AppCompatActivity {

  private ArrayList<DashboardModel> arrayList;

  @BindView(R.id.rv_dashboard)
  RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dashboard);

    ButterKnife.bind(this);

    addData();
  }

  private void addData() {
    arrayList = new ArrayList<>();
    arrayList.add(new DashboardModel("Beranda", R.drawable.beranda, JadwalActivity.class));
    arrayList.add(new DashboardModel("Layanan", R.drawable.beranda, JadwalActivity.class));
    arrayList.add(new DashboardModel("Konsultasi", R.drawable.konsultasi, JadwalActivity.class));
    arrayList.add(new DashboardModel("Antrian", R.drawable.antrian, JadwalActivity.class));
    arrayList.add(new DashboardModel("Rekap Balita", R.drawable.rekap_bayi, JadwalActivity.class));

    DashboardAdapter adapter = new DashboardAdapter(this, arrayList);
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(adapter);

    adapter.setOnItemClickListener(new DashboardAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), arrayList.get(position).getActivity());
        startActivity(intent);
      }
    });
  }
}
