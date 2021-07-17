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
import android.widget.Toast;

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
import java.util.Arrays;

public class Registrasi extends AppCompatActivity {
  private String TAG = "Registrasi";
  private String idPengguna, nmUser, kdUnik;

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

  public void RegistrasiAkun(EditText usms, EditText pswds, EditText repswds) {
    String pswd = pswds.getText().toString().trim();
    String repswd = repswds.getText().toString().trim();
    if(pswd.equals(repswd)) {
      Log.d(TAG, "RegistrasiAkun: " + "Sama");
      AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_registrasi.php")
        .addBodyParameter("idPengguna", idPengguna)
        .addBodyParameter("nama", nmUser)
        .addBodyParameter("username", usms.getText().toString().trim())
        .addBodyParameter("password", pswds.getText().toString().trim())
        .addBodyParameter("kdUnik", kdUnik)
        .addBodyParameter("level", "Anggota")
        .addBodyParameter("status", "0")
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
    } else {
      Toast toast = Toast. makeText(getApplicationContext(), "Password Berbeda!", Toast. LENGTH_SHORT); toast. show();
      Log.d(TAG, "RegistrasiAkun: " + usms.getText().toString().trim());
      Log.d(TAG, "RegistrasiAkun: " + pswds.getText().toString().trim());
      Log.d(TAG, "RegistrasiAkun: " + repswds.getText().toString().trim());
    }
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

                switch (response.getString("data")) {
                  case "-":
                    kodeUnixs.setBackgroundResource(R.drawable.rounded_bg);
                    usm.setVisibility(View.GONE);
                    pswd.setVisibility(View.GONE);
                    repswd.setVisibility(View.GONE);
                    btnMakeAccount.setVisibility(View.GONE);
                    break;

                  default:
                    JSONObject jos = new JSONObject(response.getString("data"));
                    Log.d(TAG, "onResponse: " + jos);
                    idPengguna = jos.getString("idAnggota");
                    kdUnik = jos.getString("kdAnggota");
                    nmUser = jos.getString("nmAnggota");
                    kodeUnixs.setBackgroundResource(R.drawable.uniquecode_right);
                    usm.setVisibility(View.VISIBLE);
                    pswd.setVisibility(View.VISIBLE);
                    repswd.setVisibility(View.VISIBLE);
                    btnMakeAccount.setVisibility(View.VISIBLE);
                    break;
                }

                btnMakeAccount.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                    RegistrasiAkun(usm , pswd, repswd);
                  }
                });
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
