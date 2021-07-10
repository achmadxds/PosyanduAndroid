package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Layanan extends AppCompatActivity {

  private String TAG = "Layanan";
  private ArrayList<LayananModel> arrayList;

  @BindView(R.id.rv_layanan)
  RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layanan);

    ButterKnife.bind(this);

    addData();
  }

  private void addData() {
    ProgressDialog pd = new ProgressDialog(Layanan.this);
    pd.setMessage("loading...");
    pd.show();

    arrayList = new ArrayList<>();

    AndroidNetworking.get("https://posyandukudus.000webhostapp.com/API/api_layanan.php")
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {
          pd.dismiss();
          try {
            for (int i = 0; i < response.length(); i++) {
              JSONObject jo = response.getJSONObject(i);
              arrayList.add(new LayananModel(jo.getString("nama"), Beranda.class));
            }
            arrayList.add(new LayananModel(response.getJSONObject(0).getString("nama"), Beranda.class));
          } catch (JSONException e) {
            e.printStackTrace();
          }
          ShowAdapter();
        }

        @Override
        public void onError(ANError anError) {

        }
      });
  }

  private void ShowAdapter() {
    LayananAdapter adapter = new LayananAdapter(this, arrayList);
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(adapter);
  }
}