package com.example.posyanduandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KonsultasiActivity extends AppCompatActivity {

    public String TAG = "KonsultasiActivity";

    @BindView(R.id.recycler_konsultasi)
    RecyclerView recyclerView;
    @BindView(R.id.txt_chat_konsultasi)
    EditText txt_chat;
    @BindView(R.id.btn_send_konsultasi)
    Button btn_send;
    @BindView(R.id.progress_bar_konsultasi)
    ProgressBar progressBar;
    @BindView(R.id.swipe_konsultasi)
    SwipeRefreshLayout swipeRefreshLayout;

    private ProgressDialog progressDialog;

    private List<KonsultasiModel> arrayList;
    private KonsultasiAdapter adapter;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple_700), getResources().getColor(R.color.purple_700));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addData();
            }
        });

        mPrefs = getSharedPreferences("MyPrefs", 0);

        addData();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txt_chat.getText().toString().trim().isEmpty()) sendChat();
            }
        });
    }

    private void sendChat() {
        progressDialog.setMessage("Sending messages...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String idAnggota = mPrefs.getString("idAnggotaLogin", "");
        String messages = txt_chat.getText().toString();
        Date nowDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(nowDate);
        AndroidNetworking.post("https://posyandukudus.000webhostapp.com/API/api_konsultasi.php")
                .addBodyParameter("idAnggota", idAnggota)
                .addBodyParameter("isi", messages)
                .addBodyParameter("status", "Belum Terbalas")
                .addBodyParameter("tgl_masuk", date)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);
                        try {
                            if (response.getInt("status") == 0) Toast.makeText(getApplicationContext(), "Error in sending messages", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();

                        txt_chat.setText("");
                        KonsultasiModel konsultasiModel = new KonsultasiModel();
                        konsultasiModel.setIdAnggota(Integer.parseInt(idAnggota));
                        konsultasiModel.setIsi(messages);
                        konsultasiModel.setTgl_masuk(date);
                        arrayList.add(konsultasiModel);

                        adapter = new KonsultasiAdapter(getApplicationContext(), arrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(getApplicationContext(), anError.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    private void addData() {
        progressBar.setVisibility(View.VISIBLE);
        arrayList = new ArrayList<>();
        AndroidNetworking.get("https://posyandukudus.000webhostapp.com/API/api_konsultasi.php")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("status") == 1) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    // Add client chat
                                    KonsultasiModel konsultasiModel = new KonsultasiModel();
                                    konsultasiModel.setIdAnggota(jsonObject.getInt("idAnggota"));
                                    konsultasiModel.setIsi(jsonObject.getString("isi"));
                                    konsultasiModel.setTgl_masuk(jsonObject.getString("tgl_masuk"));
                                    arrayList.add(konsultasiModel);

                                    // Add admin chat
                                    if (!jsonObject.getString("balasan").trim().isEmpty()) {
                                        konsultasiModel = new KonsultasiModel();
                                        konsultasiModel.setIdAdmin(jsonObject.getString("idAdmin"));
                                        konsultasiModel.setBalasan(jsonObject.getString("balasan"));
                                        konsultasiModel.setTgl_balas(jsonObject.getString("tgl_balas"));
                                        konsultasiModel.setStatus(jsonObject.getString("status"));
                                        arrayList.add(konsultasiModel);
                                    }
                                }
                                adapter = new KonsultasiAdapter(getApplicationContext(), arrayList);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        anError.printStackTrace();
                        Toast.makeText(getApplicationContext(), anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}