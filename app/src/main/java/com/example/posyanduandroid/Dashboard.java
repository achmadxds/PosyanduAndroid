package com.example.posyanduandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
  SharedPreferences mPrefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dashboard);
    mPrefs = getSharedPreferences("MyPrefs",0);
    ButterKnife.bind(this);

    addData();
  }

  private void addData() {
    String jenis = mPrefs.getString("jenisUserLogin", "");
    String lastUsm = mPrefs.getString("usernameLogins", "");

    TextView usr = findViewById(R.id.nameLastoLogin);
    TextView jenise = findViewById(R.id.jenisLastoLogin);

    usr.setText(lastUsm.toString());
    jenise.setText(jenis.toString());

    arrayList = new ArrayList<>();
    arrayList.add(new DashboardModel("Beranda", R.drawable.beranda, Beranda.class));
    arrayList.add(new DashboardModel("Layanan", R.drawable.beranda, Layanan.class));
    arrayList.add(new DashboardModel("Konsultasi", R.drawable.konsultasi, KonsultasiActivity.class));

    switch (jenis) {
      case "Balita":
        arrayList.add(new DashboardModel("Rekap Balita", R.drawable.rekap_bayi, Rekap.class));
        break;

      case "Bumil":
        break;

      case "Lansia":
        arrayList.add(new DashboardModel("Rekap Lansia", R.drawable.rekap_bayi, Rekap_Imunisasi_Lansia.class));
        break;
    }

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

  public void Logout(View view) {
    SharedPreferences.Editor editor = mPrefs.edit();
    editor.putString("usernameLogins", "");
    editor.putString("passwordLogins", "");
    editor.commit();

    Intent in = new Intent(Dashboard.this, Login.class);
    startActivity(in);
    finish();
  }
}
