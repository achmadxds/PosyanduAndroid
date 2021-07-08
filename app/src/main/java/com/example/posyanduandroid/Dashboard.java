package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
    arrayList.add(new DashboardModel("Beranda", R.drawable.beranda, Beranda.class));
    arrayList.add(new DashboardModel("Layanan", R.drawable.beranda, RekapFisikActivity.class));
    arrayList.add(new DashboardModel("Konsultasi", R.drawable.konsultasi, RekapFisikActivity.class));
    arrayList.add(new DashboardModel("Antrian", R.drawable.antrian, Antrian.class));
    arrayList.add(new DashboardModel("Layanan Prog", R.drawable.antrian, Layanan.class));
    arrayList.add(new DashboardModel("Semua", R.drawable.rekap_bayi, semua.class));
    arrayList.add(new DashboardModel("Rekap Balita", R.drawable.rekap_bayi, Rekap.class));

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
