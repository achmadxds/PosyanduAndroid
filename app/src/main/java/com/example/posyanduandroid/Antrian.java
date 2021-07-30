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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Antrian extends AppCompatActivity {

  Integer antrianNomor;
  private String TAG = "Antrian";
  private String jamChild;
  SharedPreferences mPrefs;

  String BASE_URL;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_antrian);

    BASE_URL = getString(R.string.base_url);
    TextView nmLayanID = findViewById(R.id.NamaLayanans);
    mPrefs = getSharedPreferences("MyPrefs",0);
    String name = mPrefs.getString("NameKey", "");
    nmLayanID.setText(name);

    Button btn = findViewById(R.id.btnGetAntrian);
    String idAnggota = mPrefs.getString("idAnggotaLogin", "");
    String KodeJadwal = mPrefs.getString("KodeKey", "");
    String idAntrian = mPrefs.getString("idAntrian", "");

    CheckHaveAntrian(mPrefs, KodeJadwal, idAnggota);

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AndroidNetworking.post(BASE_URL + "API/api_input_antrian.php")
          .addBodyParameter("kdJadwal", KodeJadwal)
          .addBodyParameter("idAnggota", idAnggota)
          .addBodyParameter("parentAntrian", idAntrian)
          .addBodyParameter("jam", jamChild)
          .addBodyParameter("status", String.valueOf(1))
          .addBodyParameter("noUrut", String.valueOf(antrianNomor))
          .setPriority(Priority.LOW)
          .build()
          .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
              Log.d(TAG, "onResponsUS: " + response);
              CheckHaveAntrian(mPrefs, KodeJadwal, idAnggota);
            }

            @Override
            public void onError(ANError anError) {
              Log.d(TAG, "onError: " + anError);
            }
          });
      }
    });
  }

  public void GetFirst(SharedPreferences some, String kodeJadwals) {
    String str = some.getString("KodeKey", "");
    Log.d(TAG, "GetFirst: " + str);

    ProgressDialog pd = new ProgressDialog(Antrian.this);
    pd.setMessage("loading...");
    pd.show();

    AndroidNetworking.post(BASE_URL + "API/api_antrian.php")
      .addBodyParameter("inputKodeJadwal", str)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
          Log.d(TAG, "onResponse: " + response);
          pd.dismiss();
          try {
            AndroidNetworking.post(BASE_URL + "API/api_checkHaveParentClock.php")
              .addBodyParameter("inputKodeJadwal", kodeJadwals)
              .setPriority(Priority.LOW)
              .build()
              .getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                  Log.d(TAG, "jamGet: " + response);
                  pd.dismiss();
                  try {
                    if(response.getString("jams").equals("null")) {
                      jamChild = mPrefs.getString("jamParent", "");
                    } else {
                      jamChild = response.getString("jams");
                    }
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                }

                @Override
                public void onError(ANError anError) {
                  Log.d(TAG, "onError2: " + anError);
                }
              });
            antrianNomor = response.getInt("maxUrut");
            TextView nomorUruts = findViewById(R.id.valueIncrement);
            nomorUruts.setText(String.valueOf(antrianNomor));
          } catch (JSONException e) {
            pd.dismiss();
            e.printStackTrace();
          }
        }

        @Override
        public void onError(ANError anError) {
          pd.dismiss();
        }
      });
  }

  public void CheckHaveAntrian(SharedPreferences some, String kdJaduwal, String aidiAnggota) {
    ProgressDialog pd = new ProgressDialog(Antrian.this);
    pd.setMessage("loading...");
    pd.show();

    AndroidNetworking.post(BASE_URL + "API/api_checkUserGetAntrian.php")
      .addBodyParameter("kdJadwal", kdJaduwal)
      .addBodyParameter("idAnggota", aidiAnggota)
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
          try {
            pd.dismiss();
            switch (response.getString("msg")){
              case "Ada Isinya":
                SharedPreferences.Editor editor = mPrefs.edit();
                JSONArray datas = new JSONArray(response.getString("data"));
                for (int  i = 0; i < datas.length(); i++) {
                  JSONObject jo = datas.getJSONObject(i);
                  editor.putString("noUruts", jo.getString("noUruts"));
                  editor.putString("jamAntrian", jo.getString("jamAntrian"));
                  editor.commit();
                }

                ShowNewIntent();
                break;

              case "Kosong":
                GetFirst(some, kdJaduwal);
                break;
            }
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onError(ANError anError) {
          pd.dismiss();
        }
      });
  }

  public void ShowNewIntent() {
    Intent i = new Intent(Antrian.this, After_Antrian.class);
    startActivity(i);
    finish();
  }
}