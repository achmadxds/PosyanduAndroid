package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.BindViews;

public class Antrian extends AppCompatActivity {

  Integer antrianNomor;
  private String TAG = "Antrian";
  SharedPreferences mPrefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_antrian);

    TextView nmLayanID = findViewById(R.id.NamaLayanans);
    mPrefs = getSharedPreferences("MyPrefs",0);
    String name = mPrefs.getString("NameKey", "");
    nmLayanID.setText(name);

    GetFirst(mPrefs);
  }

  public void GetFirst(SharedPreferences some) {
    String str = some.getString("KodeKey", "");
    Log.d(TAG, "onCreate: " + str);

    AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_antrian.php")
      .addBodyParameter("inputKodeJadwal", str)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
          try {
            antrianNomor = response.getInt("maxUrut");
            TextView nomorUruts = findViewById(R.id.valueIncrement);
            nomorUruts.setText(String.valueOf(antrianNomor));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onError(ANError anError) {

        }
      });
  }


  public void IncrementValue(View view) {
    antrianNomor++;
//
    TextView valueInc = (TextView) findViewById(R.id.valueIncrement);
    valueInc.setText(String.valueOf(antrianNomor));
  }
}