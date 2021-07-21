package com.example.posyanduandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {
  private EditText username, password;
  private Button loginButton;
  private String TAG = "Login";
  private String MY_PREFS_NAME = "MyPrefs";
  private SharedPreferences sharedpreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);

    username = findViewById(R.id.uName);
    password = findViewById(R.id.passwords);
    loginButton = findViewById(R.id.btnLogin);
    sharedpreferences = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);

    String lastUsm = sharedpreferences.getString("usernameLogins", "");
    String lastPswd = sharedpreferences.getString("passwordLogins", "");

    if(lastUsm.length() > 0 && lastPswd.length() > 0) {
      Intent i = new Intent(Login.this, Dashboard.class);
      startActivity(i);
      finish();
    }

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String usm = username.getText().toString().trim();
        String pswd = password.getText().toString().trim();
        SharedPreferences.Editor editor = sharedpreferences.edit();

        if(usm.isEmpty() && pswd.isEmpty()) {
          Toast.makeText(getApplicationContext(),"Mohon isi hadulu!",Toast.LENGTH_SHORT).show();
        }

        if(!usm.isEmpty() && !pswd.isEmpty()) {
          ProgressDialog pd = new ProgressDialog(Login.this);
          pd.setMessage("loading...");
          pd.show();

          AndroidNetworking.post("https://posyandubacin.000webhostapp.com/API/api_login.php")
            .addBodyParameter("username", usm)
            .addBodyParameter("password", pswd)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(new JSONObjectRequestListener() {
              @Override
              public void onResponse(JSONObject response) {
                pd.dismiss();
                try {
                  JSONObject joj = new JSONObject(response.getString("user"));
                  Log.d(TAG, "onResponse: " + joj);

                  editor.putString("idAnggotaLogin", joj.getString("idPengguna"));
                  editor.putString("jenisUserLogin", joj.getString("jenis"));
                  editor.putString("usernameLogins", joj.getString("username"));
                  editor.putString("passwordLogins", joj.getString("password"));
                  editor.commit();
                  Intent i = new Intent(Login.this, Dashboard.class);
                  startActivity(i);
                  finish();
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }

              @Override
              public void onError(ANError anError) {
                Toast toast = Toast. makeText(getApplicationContext(), "Username / Password Salah!", Toast. LENGTH_SHORT);
                toast. show();
                pd.dismiss();
              }
            });
        }
      }
    });
  }

  public void MengLogin(View view) {
    Intent i = new Intent(Login.this, Dashboard.class);
    startActivity(i);
    finish();
  }

  public void RegistrasiView(View view) {
    Intent i = new Intent(Login.this, Registrasi.class);
    startActivity(i);

  }
}