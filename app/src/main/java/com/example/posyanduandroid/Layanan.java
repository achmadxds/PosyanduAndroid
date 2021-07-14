package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class Layanan extends AppCompatActivity {
  private String TAG = "Layanan";
  private String MY_PREFS_NAME = "MyPrefs";
  private ArrayList<LayananModel> arrayList;

  SharedPreferences sharedpreferences;

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

    sharedpreferences = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);

    AndroidNetworking.get("https://posyandukudus.000webhostapp.com/API/api_layanan.php")
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {
          pd.dismiss();
          SharedPreferences.Editor editor = sharedpreferences.edit();
          try {
            for (int i = 0; i < response.length(); i++) {
              JSONObject jo = response.getJSONObject(i);
              editor.putString("NameKey", jo.getString("kode"));
              editor.commit();
              arrayList.add(new LayananModel(jo.getString("nama"), R.drawable.service_health, Antrian.class));
            }
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

    adapter.setOnItemClickListener(new LayananAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), arrayList.get(position).getActivity());
        startActivity(intent);
      }
    });
  }
}