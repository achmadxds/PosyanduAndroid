package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    Button btn = findViewById(R.id.btnGetAntrian);
    String prntAntrian = mPrefs.getString("ParentAntrian", "");
    String idAnggota = mPrefs.getString("idAnggotaLogin", "");
    String KodeJadwal = mPrefs.getString("KodeKey", "");
    String idAntrian = mPrefs.getString("idAntrian", "");
    String jamParent = mPrefs.getString("jamParent", "");

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_antrian_input.php")
          .addBodyParameter("kdJadwal", KodeJadwal)
          .addBodyParameter("idAnggota", idAnggota)
          .addBodyParameter("parentAntrian", idAntrian)
          .addBodyParameter("jam", jamParent)
          .addBodyParameter("status", String.valueOf(1))
          .addBodyParameter("noUrut", String.valueOf(antrianNomor))
          .setPriority(Priority.MEDIUM)
          .build()
          .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
              CheckHaveAntrian(mPrefs, KodeJadwal, idAnggota);
            }

            @Override
            public void onError(ANError anError) {
              Log.d(TAG, "onError: " + anError);
            }
          });
      }
    });

    CheckHaveAntrian(mPrefs, KodeJadwal, idAnggota);
  }

  public void GetFirst(SharedPreferences some) {
    String str = some.getString("KodeKey", "");

    ProgressDialog pd = new ProgressDialog(Antrian.this);
    pd.setMessage("loading...");
    pd.show();

    AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_antrian.php")
      .addBodyParameter("inputKodeJadwal", str)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
          pd.dismiss();
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

  public void CheckHaveAntrian(SharedPreferences some, String kdJaduwal, String aidiAnggota) {
    ProgressDialog pd = new ProgressDialog(Antrian.this);
    pd.setMessage("loading...");
    pd.show();

    AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_checkUserGetAntrian.php")
      .addBodyParameter("kdJadwal", kdJaduwal)
      .addBodyParameter("idAnggota", aidiAnggota)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
          pd.dismiss();
          try {
            switch (response.getString("msg")){
              case "Ada Isinya":
                SharedPreferences.Editor editor = mPrefs.edit();
                JSONObject datas = new JSONObject(response.getString("data"));
                editor.putString("noUruts", datas.getString("noUrut"));
                editor.putString("jamAntrian", datas.getString("jam"));
                editor.commit();

                ShowNewIntent();
                break;

              case "Kosong":
                GetFirst(some);
                break;
            }
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

  public void ShowNewIntent() {
    Intent i = new Intent(Antrian.this, After_Antrian.class);
    startActivity(i);
    finish();
  }
}