package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rekap_Imunisasi_Lansia extends AppCompatActivity {

  private static final String MY_PREFS_NAME = "MyPrefs";
  private String TAG = "RekapFisikActivity";

  @BindView(R.id.rv_rekap_imunisasi_lansia)
  RecyclerView recyclerView;

  private Rekap_Imunisasi_Lansia_Adapter rekapImunAdapter;
  private ArrayList<Rekap_Imunisasi_Lansia_Model> rekapImunArray;
  private SharedPreferences sharedpreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rekap_imunisasi_lansia);

    ButterKnife.bind(this);

    addData();
  }

  public void addData() {
    rekapImunArray = new ArrayList<>();
    sharedpreferences = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
    String lastAidi = sharedpreferences.getString("idAnggotaLogin", "");

    AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_lansia_vaksin.php")
      .addBodyParameter("aidiAnggota", lastAidi)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {
          try {
            for (int o = 0; o < response.length(); o++) {
              JSONObject jo = response.getJSONObject(o);
              Log.d(TAG, "onResponse: " + jo);
              rekapImunArray.add(new Rekap_Imunisasi_Lansia_Model(jo.getString("nmImunisasi"), jo.getString("tanggal")));
            }

            rekapImunAdapter = new Rekap_Imunisasi_Lansia_Adapter(this, rekapImunArray);
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