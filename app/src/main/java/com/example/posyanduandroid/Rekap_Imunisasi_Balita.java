package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rekap_Imunisasi_Balita extends AppCompatActivity {

  private String TAG = "RekapFisikActivity";
  @BindView(R.id.rv_rekap_imunisasi_balita)
  RecyclerView recyclerView;

  private Rekap_Imunisasi_Balita_Adapter rekapImunAdapter;
  private ArrayList<Rekap_Imunisasi_Balita_Model> rekapImunArray;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rekap_imunisasi_balita);

    ButterKnife.bind(this);

    addData();
  }

  private void addData() {
    rekapImunArray = new ArrayList<>();

    AndroidNetworking.get("https://posyandukudus.000webhostapp.com/API/api_balita_vaksin.php")
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {
          try {
            for (int o = 0; o < response.length(); o++) {
              JSONObject jo = response.getJSONObject(o);
              Log.d(TAG, "onResponse: " + jo);
              rekapImunArray.add(new Rekap_Imunisasi_Balita_Model(jo.getString("tanggal"), jo.getString("nmImunisasi")));
            }

            rekapImunAdapter = new Rekap_Imunisasi_Balita_Adapter(this, rekapImunArray);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(rekapImunAdapter);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onError(ANError anError) {
          Log.d(TAG, "onError: " + anError);
        }
      });
  }
}