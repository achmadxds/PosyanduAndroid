package com.example.posyanduandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class semua extends AppCompatActivity {
    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semua);

        listView = (ListView) findViewById(R.id.listViewS);

        getJSON();

    }
    private void showEmployee(){

        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);


                String kode = jo.getString(konfigurasi.TAG_KODE);
                String nama = jo.getString(konfigurasi.TAG_NAMA);
                String jenis = jo.getString(konfigurasi.TAG_JENIS);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_KODE,kode);
                employees.put(konfigurasi.TAG_NAMA,nama);
                employees.put(konfigurasi.TAG_JENIS,jenis);

                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                semua.this,
                list,
                R.layout.activity_list_item,
                new String[]{
                        konfigurasi.TAG_KODE,
                        konfigurasi.TAG_NAMA,
                        konfigurasi.TAG_JENIS,
                },
                new int[]{
                        R.id.kode,
                        R.id.nama,
                        R.id.jenis

                }
        );

        listView.setAdapter(adapter);
    }

    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(
                        semua.this,
                        "Mengambil Data","Mohon Tunggu...",
                        false,
                        false
                );
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequest(konfigurasi.URL_LAYANAN);
            }
        }

        GetJSON gj = new GetJSON();
        gj.execute();
    }


}