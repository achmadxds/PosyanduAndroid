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
    SharedPreferences.Editor editor = sharedpreferences.edit();

    AndroidNetworking.get("https://posyandubacin.000webhostapp.com/API/api_layanan.php")
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {
          pd.dismiss();
          try {
            for (int i = 0; i < response.length(); i++) {
              JSONObject jo = response.getJSONObject(i);
              if(jo.getString("parentAntrian").equals("0")) {
                arrayList.add(new LayananModel(jo.getString("nama"), R.drawable.service_health, Antrian.class, jo.getString("kodeJadwal"), jo.getString("parentAntrian"), jo.getString("idAntrian"), jo.getString("jamParent")));
              }
            }
          } catch (JSONException e) {
            e.printStackTrace();
          }
          ShowAdapter(editor);
        }

        @Override
        public void onError(ANError anError) {

        }
      });
  }

  private void ShowAdapter(SharedPreferences.Editor editor) {
    LayananAdapter adapter = new LayananAdapter(this, arrayList);
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(adapter);

    adapter.setOnItemClickListener(new LayananAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(int position) {
        editor.putString("KodeKey", arrayList.get(position).getKodeJadwal());
        editor.putString("NameKey", arrayList.get(position).getTitle());
        editor.putString("ParentAntrian", arrayList.get(position).getParentAntrian());
        editor.putString("idAntrian", arrayList.get(position).getIdAntrian());
        editor.putString("jamParent", arrayList.get(position).getJamParent());
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), arrayList.get(position).getActivity());
        startActivity(intent);
        finish();
      }
    });
  }
}