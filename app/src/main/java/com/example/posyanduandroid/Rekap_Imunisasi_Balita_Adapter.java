package com.example.posyanduandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rekap_Imunisasi_Balita_Adapter extends RecyclerView.Adapter<Rekap_Imunisasi_Balita_Adapter.ListViewHolder> {
  private ArrayList<Rekap_Imunisasi_Balita_Model> dataList;
  private Rekap_Imunisasi_Balita_Adapter.OnItemClickListener mListener;
  private JSONArrayRequestListener mContext;

  public Rekap_Imunisasi_Balita_Adapter(JSONArrayRequestListener mContext, ArrayList<Rekap_Imunisasi_Balita_Model> dataList) {
    this.mContext = mContext;
    this.dataList = dataList;
  }

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  public void setOnItemClickListener(Rekap_Imunisasi_Balita_Adapter.OnItemClickListener listener) {
    mListener = listener;
  }

  @Override
  public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.activity_rekap_imunisasi_balita_list, parent, false);
    return new Rekap_Imunisasi_Balita_Adapter.ListViewHolder(view, mListener);
  }

  @Override
  public void onBindViewHolder(ListViewHolder holder, int position) {
    if (holder.getAdapterPosition() == 0) {
      holder.namaRekapImunisasiBalita.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.tanggalRekapImunisasiBalita.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.descRekapImunisasiBalita.setBackgroundResource(R.drawable.table_header_cell_bg);

      holder.namaRekapImunisasiBalita.setText("Imunisasi");
      holder.tanggalRekapImunisasiBalita.setText("Tanggal");
      holder.descRekapImunisasiBalita.setText("Deskripsi");

    } else {
      holder.namaRekapImunisasiBalita.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.tanggalRekapImunisasiBalita.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.descRekapImunisasiBalita.setBackgroundResource(R.drawable.table_content_cell_bg);

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
    @BindView(R.id.tv_nama_rekapImunisasiBalita)
    TextView namaRekapImunisasiBalita;
    @BindView(R.id.tv_tanggal_rekapImunisasiBalita)
    TextView tanggalRekapImunisasiBalita;
    @BindView(R.id.tv_desc_rekapImunisasiBalita)
    TextView descRekapImunisasiBalita;

    public ListViewHolder(View itemView, OnItemClickListener mListener) {
      super(itemView);
      ButterKnife.bind(this, itemView);

    }
  }
}
