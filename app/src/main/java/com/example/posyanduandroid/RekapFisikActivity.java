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
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RekapFisikActivity extends AppCompatActivity {

  private static final String MY_PREFS_NAME = "MyPrefs";
  private String TAG = "RekapFisikActivity";
  @BindView(R.id.rv_jadwal)
  RecyclerView recyclerView;

  String BASE_URL;

  private RekapFisikAdapter rekapFisikAdapter;
  private ArrayList<RekapFisikModel> rekapFisikArray;
  private SharedPreferences sharedpreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rekapfisikbalita);

    BASE_URL = getString(R.string.base_url);
    ButterKnife.bind(this);

    addData();
  }

  private void addData() {
    rekapFisikArray = new ArrayList<>();
    sharedpreferences = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
    String lastAidi = sharedpreferences.getString("idAnggotaLogin", "");

    AndroidNetworking.post(BASE_URL + "API/api_balita_fisik.php")
      .addBodyParameter("aidiAnggota", lastAidi)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {
          try {
            for (int i = 0; i < response.length(); i++) {
              JSONObject jo = response.getJSONObject(i);
              Log.d(TAG, "onResponse: " + jo);
              rekapFisikArray.add(new RekapFisikModel(jo.getString("nm_balita"), jo.getString("berat") + " Kg", jo.getString("tanggal"), jo.getString("panjang") + " Kg"));
            }

            rekapFisikAdapter = new RekapFisikAdapter(this, rekapFisikArray);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(rekapFisikAdapter);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
        @Override
        public void onError(ANError error) {
          Log.d(TAG, "onError: " + error);
        }
      });
  }
}