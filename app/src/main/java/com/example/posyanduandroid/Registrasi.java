package com.example.posyanduandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class Registrasi extends AppCompatActivity {
  private String TAG = "Registrasi";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AlertDialog alertDialog = new AlertDialog.Builder(Registrasi.this).create(); //Read Update
    alertDialog.setTitle("PESAN!!");
    alertDialog.setMessage("Pastikan Anda Sudah Punya Kode Unik Dari Poyandu!!");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.registrasi);
    alertDialog.show();

    CheckUniqueCode();
  }

  public void RegistrasiAkun() {
    AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_checkUniqueCode.php")
      .addBodyParameter("idPengguna", "")
      .addBodyParameter("nama", "")
      .addBodyParameter("username", "")
      .addBodyParameter("password", "")
      .addBodyParameter("kdUnik", "")
      .addBodyParameter("level", "")
      .addBodyParameter("status", "")
      .setPriority(Priority.LOW)
      .build()
      .getAsJSONArray(new JSONArrayRequestListener() {
        @Override
        public void onResponse(JSONArray response) {

        }

        @Override
        public void onError(ANError anError) {

        }
      });

  }

  public void CheckUniqueCode() {
    EditText uniqueCode = findViewById(R.id.uniquecode);
    uniqueCode.addTextChangedListener(new TextWatcher() {
      public void onTextChanged(CharSequence cs, int s, int b, int c) {
        AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_checkUniqueCode.php")
          .addBodyParameter("kodeUnik", cs.toString())
          .setPriority(Priority.LOW)
          .build()
          .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
              try {
                EditText kodeUnixs = findViewById(R.id.uniquecode);
                EditText usm = findViewById(R.id.daftarUsername);
                EditText pswd = findViewById(R.id.daftarPassword);
                EditText repswd = findViewById(R.id.daftarRePassword);
                Button btnMakeAccount = findViewById(R.id.btnMakeAccount);

                switch (response.) {
                  case "-":
                    kodeUnixs.setBackgroundResource(R.drawable.rounded_bg);
                    usm.setVisibility(View.GONE);
                    pswd.setVisibility(View.GONE);
                    repswd.setVisibility(View.GONE);
                    btnMakeAccount.setVisibility(View.GONE);
                    break;

                  default:
                    String a = response.getString("data");
                    Log.i(TAG, "onResponse: " + a);
                    kodeUnixs.setBackgroundResource(R.drawable.uniquecode_right);
                    usm.setVisibility(View.VISIBLE);
                    pswd.setVisibility(View.VISIBLE);
                    repswd.setVisibility(View.VISIBLE);
                    btnMakeAccount.setVisibility(View.VISIBLE);
                    break;
                }
              } catch (JSONException e) {
                e.printStackTrace();
              }
            }

            @Override
            public void onError(ANError anError) {
              Log.i(TAG, "onError: " + anError);
            }
          });
      }

      @Override
      public void afterTextChanged(Editable s) { }

      public void beforeTextChanged(CharSequence cs, int i, int j, int k) { }
    });
  }

  public void Kembali(View view) {
    Intent intent= new Intent(Registrasi.this, Login.class);
    startActivity(intent);
  }
}
