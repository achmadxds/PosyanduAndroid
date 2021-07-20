package com.example.posyanduandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rekap_Imunisasi_Lansia_Adapter extends RecyclerView.Adapter<Rekap_Imunisasi_Lansia_Adapter.ListViewHolder> {
  private ArrayList<Rekap_Imunisasi_Lansia_Model> dataList;
  private Rekap_Imunisasi_Lansia_Adapter.OnItemClickListener mListener;
  private JSONArrayRequestListener mContext;

  public Rekap_Imunisasi_Lansia_Adapter(JSONArrayRequestListener mContext, ArrayList<Rekap_Imunisasi_Lansia_Model> dataList) {
    this.mContext = mContext;
    this.dataList = dataList;
  }

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  public void setOnItemClickListener(Rekap_Imunisasi_Lansia_Adapter.OnItemClickListener listener) {
    mListener = listener;
  }

  @Override
  public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.activity_rekap_imunisasi_lansia_list, parent, false);
    return new Rekap_Imunisasi_Lansia_Adapter.ListViewHolder(view, mListener);
  }

  @Override
  public void onBindViewHolder(Rekap_Imunisasi_Lansia_Adapter.ListViewHolder holder, int position) {
    if (holder.getAdapterPosition() == 0) {
      holder.namaRekapImunisasiBalita.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.tanggalRekapImunisasiBalita.setBackgroundResource(R.drawable.table_header_cell_bg);

      holder.namaRekapImunisasiBalita.setText("Nama Imunisasi");
      holder.tanggalRekapImunisasiBalita.setText("Tanggal");

    } else {
      holder.namaRekapImunisasiBalita.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.tanggalRekapImunisasiBalita.setBackgroundResource(R.drawable.table_content_cell_bg);

      int pos = position -1;
      holder.namaRekapImunisasiBalita.setText(dataList.get(pos).getNmImunisasi());
      holder.tanggalRekapImunisasiBalita.setText(dataList.get(pos).getTglImunisasi());
    }
  }

  @Override
  public int getItemCount() {
    return dataList.size() + 1;
  }

  public class ListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_nama_rekapImunisasiLansia)
    TextView namaRekapImunisasiBalita;
    @BindView(R.id.tv_tanggal_rekapImunisasiLansia)
    TextView tanggalRekapImunisasiBalita;

    public ListViewHolder(View itemView, OnItemClickListener mListener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
