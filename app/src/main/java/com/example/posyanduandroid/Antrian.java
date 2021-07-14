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

  Integer angka = 1;
  private String MY_PREFS_NAME;
  private String TAG = "Antrian";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_antrian);
    SharedPreferences mPrefs = getSharedPreferences("MyPrefs",0);
    String str = mPrefs.getString("NameKey", "");

    AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_antrian.php")
      .addBodyParameter("inputKodeJadwal", str)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {
          Log.d(TAG, "onResponse: " + response);
        }

        @Override
        public void onError(ANError anError) {
          Log.d(TAG, "onError: " + anError);
        }
      });
  }

  public void IncrementValue(View view) {
    angka++;

    TextView valueInc = (TextView) findViewById(R.id.valueIncrement);
    valueInc.setText(String.valueOf(angka));
  }
}